public enum EixoTematico {
    EXATAS("1"),
    BIOLOGICAS("2"),
    SAUDE("3"),
    AGRARIAS("4"),
    HUMANAS("5"),
    SOCIAIS("6"),
    ENGENHARIAS("7"),
    LINGUISTICA("8");

    private final String menu_choice;
        EixoTematico(String menu_choice){
            this.menu_choice = menu_choice;
        }
        public String getClassificacao(){
            return this.menu_choice;
        }
    
    public static EixoTematico valuesOfLabel(String label){
        for (EixoTematico e : values()) {
            if (e.menu_choice.equals(label)){
                return e;
            }
        }
        return null;
    }
}
