public class PesquisaSO {
    public static final int WINDOWS_SERVER = 1;
    public static final int UNIX = 2;
    public static final int LINUX = 3;
    public static final int NETWARE = 4;
    public static final int MAC_OS = 5;
    public static final int OUTRO = 6;
    public static final int EXIT = 0;

    private int pesquisa[] = new int[7];
    private int numero_entradas;
    private int entradas_realizadas = 0;

    public PesquisaSO(int numero_entradas){
        this.numero_entradas = numero_entradas; 
    }

    public boolean addVote(int vote){
        if (vote_ended()){
            return false;
        } else{
            switch (vote){
                case WINDOWS_SERVER:{pesquisa[WINDOWS_SERVER]++; break;}
                case UNIX:{pesquisa[UNIX]++; break;}
                case LINUX:{pesquisa[LINUX]++; break;}
                case NETWARE:{pesquisa[NETWARE]++; break;}
                case MAC_OS:{pesquisa[MAC_OS]++; break;}
                case OUTRO:{pesquisa[OUTRO]++; break;}
                default :{return false;}
            }
            this.entradas_realizadas++;
            return true;
        }
    }

    public boolean vote_ended(){
        if (this.numero_entradas == this.entradas_realizadas){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        int number_perc_str = 5;
        String str = "\n";
        str += String.format("%-15s %5s %s\n", "SO", "Votos", center_string("%", number_perc_str));
        str += String.format("%-15s %5s %s\n", "-------------", "-----", center_string("---", number_perc_str));
        str += String.format("%-15s %5d %s\n", "Windows Server", pesquisa[WINDOWS_SERVER], center_string(votes_percent(WINDOWS_SERVER), number_perc_str));
        str += String.format("%-15s %5d %s\n", "Unix", pesquisa[UNIX], center_string(votes_percent(UNIX), number_perc_str));
        str += String.format("%-15s %5d %s\n", "Linux", pesquisa[LINUX], center_string(votes_percent(LINUX), number_perc_str));
        str += String.format("%-15s %5d %s\n", "Netware", pesquisa[NETWARE], center_string(votes_percent(NETWARE), number_perc_str));
        str += String.format("%-15s %5d %s\n", "Mac OS", pesquisa[MAC_OS], center_string(votes_percent(MAC_OS), number_perc_str));
        str += String.format("%-15s %5d %s\n", "Outro", pesquisa[OUTRO], center_string(votes_percent(OUTRO), number_perc_str));
        str += String.format("%-15s %5s\n", "-------------", "-----");
        str += String.format("%-15s %5d\n", "Total", this.entradas_realizadas);

        return str;
    }

    public String votes_percent(int os){
        return "" + (int)(((float)pesquisa[os]/(float)this.entradas_realizadas)*100.0) + "%";
    }

    public String center_string(String txt, int number_caracteres){
        int len = txt.length();
        if (len >= number_caracteres){
            return txt;
        }
        String white_spaces_str = "";
        int white_spaces = (number_caracteres - len)/2;
        for (int i = 0; i < white_spaces; i++ ){
            white_spaces_str += " ";
        }
        return white_spaces_str + txt + white_spaces_str;
    }
}