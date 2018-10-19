package utilidades;

public enum Regex {
    CEDULA("([0-9]){10}"),
    EMAIL("([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)|"),
    LETRASESPACIO("[a-zA-Z\\s]*"),
    TELEFONO ("(([0-9])){10}|"),
    NUMERODECIMAL2 ("\\d+(\\.\\d{2})?|"),
    NUMEROS ("\\d+|"),
    HORAS24("([01]{1}[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?|");

    private String expresion;

    Regex(String s) {
        this.expresion = s;
    }

    public String getExpresion() {
        return expresion;
    }
}
