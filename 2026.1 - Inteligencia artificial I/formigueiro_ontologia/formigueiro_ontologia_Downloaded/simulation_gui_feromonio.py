import asyncio
import tkinter as tk
from tkinter import ttk


class SimulationGUI:
    def __init__(self, root, ants, env, on_pause, on_reset, on_cancel):
        self.root = root
        self.ants = ants
        self.env = env
        self.scale = 25
        self.offset = 300
        self.paused = False
        
        self.root.title("Simulação Avançada do Formigueiro")
        self.root.geometry("800x800")
        self.root.configure(bg="#f5f5f5")

        # Top Status Bar
        self.status_frame = tk.Frame(root, bg="#ffffff", pady=10)
        self.status_frame.pack(fill=tk.X)
        self.ant_labels = {}
        for i, ant in enumerate(ants):
            row = i // 8
            col = i % 8
            lbl = tk.Label(self.status_frame, text=f"{ant.name}: ⏳", fg=ant.color, font=("Arial", 9, "bold"), bg="#ffffff")
            lbl.grid(row=row, column=col, padx=2, pady=2, sticky="w")
            self.ant_labels[ant.name] = lbl
        
        # Configure grid columns to be proportional
        for i in range(8):
            self.status_frame.columnconfigure(i, weight=1)


        # Canvas
        self.canvas = tk.Canvas(root, width=600, height=520, bg="#ffffff", bd=2, relief=tk.SUNKEN)
        self.canvas.pack(pady=5)
        
        # Bottom Controls
        self.control_frame = tk.Frame(root, bg="#f5f5f5", pady=10)
        self.control_frame.pack(fill=tk.X)
        
        # Inner frame to center buttons
        self.btn_inner_frame = tk.Frame(self.control_frame, bg="#f5f5f5")
        self.btn_inner_frame.pack(anchor="center")

        self.btn_pause = ttk.Button(self.btn_inner_frame, text="Pausar", command=on_pause)
        self.btn_pause.pack(side=tk.LEFT, padx=10)
        
        self.btn_reset = ttk.Button(self.btn_inner_frame, text="Resetar", command=on_reset)
        self.btn_reset.pack(side=tk.LEFT, padx=10)
        
        self.btn_cancel = ttk.Button(self.btn_inner_frame, text="Cancelar", command=on_cancel)
        self.btn_cancel.pack(side=tk.LEFT, padx=10)

        self.info_label = tk.Label(root, text="", font=("Arial", 12, "bold"), bg="#f5f5f5")
        self.info_label.pack()
        
        self.steps_label = tk.Label(root, text="Passos Totais: 0", font=("Arial", 10), bg="#f5f5f5", fg="#555555")
        self.steps_label.pack()

    def update_view(self, total_steps):
        self.canvas.delete("all")
        
        # Draw Grid
        for i in range(0, 601, self.scale):
            self.canvas.create_line(i, 0, i, 600, fill="#eeeeee")
            self.canvas.create_line(0, i, 600, i, fill="#eeeeee")

        # Draw Persistent Paths
        for ant in self.ants:
            for path in ant.all_paths:
                if len(path) > 1:
                    points = []
                    for p in path:
                        px, py = self.to_screen(p)
                        points.extend([px, py])
                    self.canvas.create_line(*points, fill=ant.color, width=1, stipple="gray50")

        # Draw Pheromones
        for pos, next_pos in self.env.pheromone.items():
            x1, y1 = self.to_screen(pos)
            x2, y2 = self.to_screen(next_pos)
            self.canvas.create_line(x1, y1, x2, y2, fill="#FF69B4", width=3, dash=(4, 2))

        # Draw Tamandua
        if self.env.tamandua_pos:
            tx, ty = self.to_screen(self.env.tamandua_pos)
            self.canvas.create_rectangle(tx-12, ty-12, tx+12, ty+12, fill="#000000", outline="#FF0000", width=2)
            self.canvas.create_text(tx, ty-20, text="TAMANDUÁ", fill="#FF0000", font=("Arial", 8, "bold"))

        # Draw Base
        bx, by = self.to_screen((0,0))
        self.canvas.create_oval(bx-12, by-12, bx+12, by+12, fill="#8B4513", outline="#5D2E0A")
        self.canvas.create_text(bx, by+25, text="BASE", font=("Arial", 8, "bold"))

        # Draw Food
        if not self.env.is_exhausted():
            fx, fy = self.to_screen(self.env.food_pos)
            self.canvas.create_rectangle(fx-10, fy-10, fx+10, fy+10, fill="#FFD700", outline="#DAA520")
            self.canvas.create_text(fx, fy-20, text=f"COMIDA ({self.env.capacity - self.env.collected})", font=("Arial", 8, "bold"))

        # Draw Ants and Current Path
        for ant in self.ants:
            # Draw current path
            if len(ant.history) > 1:
                pts = []
                for p in ant.history:
                    ax, ay = self.to_screen(p)
                    pts.extend([ax, ay])
                self.canvas.create_line(*pts, fill=ant.color, width=2)

            # Draw Ant
            ax, ay = self.to_screen(ant.pos)
            if ant.state == "DEAD":
                # Draw an X for dead ant
                self.canvas.create_line(ax-6, ay-6, ax+6, ay+6, fill="red", width=2)
                self.canvas.create_line(ax-6, ay+6, ax+6, ay-6, fill="red", width=2)
                status = "💀"
            else:
                self.canvas.create_oval(ax-6, ay-6, ax+6, ay+6, fill=ant.color, outline="black")
                status = "✅" if ant.collected_food else "⏳"
            
            # Update status labels
            self.ant_labels[ant.name].config(text=f"{ant.name}: {status}")

        self.info_label.config(text=f"Total Coletado: {self.env.collected}/{self.env.capacity}")
        self.steps_label.config(text=f"Soma de Passos de todas as Formigas: {total_steps}")

    def to_screen(self, pos):
        x, y = pos
        return x * self.scale + self.offset, -y * self.scale + self.offset
