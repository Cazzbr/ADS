public class Caracteres {
    private char caracteres[];

    public Caracteres(int number_of_caracteres){
        caracteres = new char[number_of_caracteres];
    }

    public void fillArrayCaracteres(char c) {
        for (int i = 0; i < caracteres.length; i++) {
            if (caracteres[i] == '\0'){
                caracteres[i] = c;
                break;
            }
        }
    }

    public String getConsonants(){
        String str = "";
        str += "   *** Array de Caracteres  - Consoantes *** \n";
        str += " Elas São: | ";
        for (char c : caracteres) {
            if (isConsoant(c)){
                str += c + " | ";
            }
        }
        return str;
    }

    public char[] getCaracteres() {
        return caracteres;
    }
    public void setCaracteres(char c, int position) {
        this.caracteres[position] = c;
    }

    public int getNumberOfConsonants() {
        int number_of_consonants = 0;

        for (char c : caracteres) {
            if (isConsoant(c)){
                number_of_consonants++;
            }
        }
        return number_of_consonants;
    }

    private boolean isConsoant(char ch) {
        int ascii = (int) ch;
        if ((ascii >= 66 && ascii <= 90) || (ascii >= 98 && ascii <= 122) || ascii == 231 || ascii == 199){
            if (ascii != 69 && ascii != 73 && ascii != 79 && ascii != 85 &&
            ascii != 101 && ascii != 105 && ascii != 111 &&  ascii != 117){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String str = "\n";
        str += "   ******** Array de Caracteres ******** \n";
        str += " | ";
        for (char c : caracteres) {
            str += c + " | ";
        }
        str += "\n";
        return str;
    }
}