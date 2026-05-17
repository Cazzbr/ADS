"""
formigueiro_feromonio.py – Loop principal da simulação orquestrado pela ontologia.

Toda a configuração (número de formigas, capacidade de alimento, intervalo
do tamanduá) é extraída da ontologia formigueiro.ttl via OntologyManager.
Nenhum valor vem hardcoded ou do .env — a ontologia é a fonte única de verdade.
"""

import asyncio
import random
import tkinter as tk
from tkinter import ttk

from ontology_manager import OntologyManager
from environment_feromonio import Environment
from ant_feromonio import Ant
from simulation_gui_feromonio import SimulationGUI


async def main():
    # ── Carrega a ontologia como fonte única de verdade ──
    onto = OntologyManager()

    # Lê parâmetros globais da ontologia
    num_formigas = onto.get_populacao_total()
    capacidade_alimento = onto.get_capacidade_alimento()
    intervalo_tamandua = onto.get_intervalo_tamandua()
    formigas_info = onto.get_formigas()

    print(f"=== ONTOLOGIA CARREGADA ===")
    print(f"  População: {num_formigas}")
    print(f"  Capacidade de alimento: {capacidade_alimento}")
    print(f"  Intervalo do tamanduá: {intervalo_tamandua}s")
    print(f"  Formigas definidas: {len(formigas_info)}")
    print(f"===========================")

    state = {"running": True, "paused": False, "reset": False}
    pause_event = asyncio.Event()
    pause_event.set()

    def toggle_pause():
        state["paused"] = not state["paused"]
        if state["paused"]:
            pause_event.clear()
            gui.btn_pause.config(text="Continuar")
        else:
            pause_event.set()
            gui.btn_pause.config(text="Pausar")

    def reset():
        state["reset"] = True

    def cancel():
        state["running"] = False
        root.destroy()

    # Cores para as formigas
    colors = [
        "#FF4500", "#1E90FF", "#32CD32", "#9370DB", "#FF8C00",
        "#00CED1", "#FF1493", "#8B4513", "#2F4F4F", "#B22222",
        "#DAA520", "#7B68EE", "#20B2AA", "#CD5C5C", "#4682B4",
        "#D2691E", "#6A5ACD", "#3CB371", "#DB7093", "#556B2F",
    ]

    while state["running"]:
        state["reset"] = False

        # Reseta contadores na ontologia para novo ciclo
        onto.resetar_contadores()

        # Restringe a (-9, 9) para garantir que fique visível dentro das linhas do grid
        food_pos = (random.randint(-9, 9), random.randint(-9, 9))
        if abs(food_pos[0]) < 2 and abs(food_pos[1]) < 2:
            food_pos = (5, 5)

        # Gerar tamanduá
        tamandua_pos = (random.randint(-9, 9), random.randint(-9, 9))
        while (abs(tamandua_pos[0]) < 2 and abs(tamandua_pos[1]) < 2) or tamandua_pos == food_pos:
            tamandua_pos = (random.randint(-9, 9), random.randint(-9, 9))

        # Registra posição do tamanduá na ontologia
        onto.atualizar_posicao_tamandua(tamandua_pos)

        # Cria ambiente com referência ao OntologyManager
        env = Environment(onto_manager=onto, food_pos=food_pos, tamandua_pos=tamandua_pos)

        # ── Cria formigas com dados da ontologia ──
        # Usa até num_formigas agentes (conforme populacaoTotal da ontologia)
        formigas_a_criar = formigas_info[:num_formigas]
        ant_names = [fi["nome"] for fi in formigas_a_criar]

        inboxes = {name: asyncio.Queue() for name in ant_names}
        ants = []
        for i, fi in enumerate(formigas_a_criar):
            ant = Ant(
                name=fi["nome"],
                env=env,
                all_ants_inboxes=inboxes,
                color=colors[i % len(colors)],
                onto_manager=onto,
                nome_onto=fi["nome_onto"],
            )
            ant.pause_event = pause_event
            ants.append(ant)

        # UI Setup
        if 'root' not in locals():
            root = tk.Tk()
            gui = SimulationGUI(root, ants, env, toggle_pause, reset, cancel)
        else:
            gui.ants = ants
            gui.env = env
            for ant in ants:
                gui.ant_labels[ant.name].config(text=f"{ant.name}: ⏳")

        # ── Tamanduá: intervalo de movimento vindo da ontologia ──
        async def mover_tamandua():
            ticks_por_ciclo = int(intervalo_tamandua / 0.05)  # converte segundos → ticks
            while state["running"] and not state["reset"]:
                for _ in range(ticks_por_ciclo):
                    await asyncio.sleep(0.05)
                    if state["reset"] or not state["running"]:
                        return
                    if state["paused"]:
                        await pause_event.wait()

                if env.is_exhausted() or all(a.state in ["FINISHED", "DEAD"] for a in ants):
                    break

                nova_pos = (random.randint(-9, 9), random.randint(-9, 9))
                while (abs(nova_pos[0]) < 2 and abs(nova_pos[1]) < 2) or nova_pos == env.food_pos:
                    nova_pos = (random.randint(-9, 9), random.randint(-9, 9))
                env.tamandua_pos = nova_pos
                # Registra nova posição na ontologia
                onto.atualizar_posicao_tamandua(nova_pos)
                print(f"--- AVISO: O Tamanduá se moveu para {nova_pos}! ---")

        tasks = [asyncio.create_task(ant.agir()) for ant in ants]
        tasks.append(asyncio.create_task(mover_tamandua()))

        print(f"--- Nova Busca Iniciada - Comida em {food_pos} ---")

        try:
            while not state["reset"] and state["running"]:
                total_steps = sum(
                    len(a.history) - 1 + sum(len(p) - 1 for p in a.all_paths) for a in ants
                )
                gui.update_view(total_steps)
                root.update_idletasks()
                root.update()

                all_stopped = all(a.state in ["FINISHED", "DEAD"] for a in ants)
                if all_stopped:
                    if env.is_exhausted():
                        print("Ciclo finalizado: Comida esgotada e formigas pararam.")
                    else:
                        print("Ciclo finalizado: Todas as formigas morreram ou pararam.")
                    break

                await asyncio.sleep(0.05)

            if state["reset"]:
                print("Ciclo reiniciado por solicitação (Reset).")

            if not state["running"]:
                print("Simulação encerrada pelo usuário.")
                break

            if (env.is_exhausted() or all_stopped) and not state["reset"]:
                total_steps = sum(
                    len(a.history) - 1 + sum(len(p) - 1 for p in a.all_paths) for a in ants
                )
                gui.update_view(total_steps)
                for i in range(5, 0, -1):
                    if state["reset"] or not state["running"]:
                        break
                    msg = "ALIMENTO ESGOTADO!" if env.is_exhausted() else "COLÔNIA EXTINTA!"
                    gui.info_label.config(text=f"{msg} Reiniciando em {i}s...")
                    for _ in range(20):
                        root.update_idletasks()
                        root.update()
                        await asyncio.sleep(0.05)

            for t in tasks:
                t.cancel()
            await asyncio.gather(*tasks, return_exceptions=True)

        except tk.TclError:
            state["running"] = False
            break

    # Salva o resultado final no disco
    onto.salvar_ontologia()


if __name__ == "__main__":
    try:
        asyncio.run(main())
    except (asyncio.CancelledError, KeyboardInterrupt):
        pass
