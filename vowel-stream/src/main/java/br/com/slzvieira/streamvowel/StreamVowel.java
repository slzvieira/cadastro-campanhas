/**
 * 
 */
package br.com.slzvieira.streamvowel;

/**
 * DOCUMENT ME
 * @author slvieira
 */
public class StreamVowel implements Stream {

    private static final String VOWEL_STRING = "AEIOU";
    private static final String CONSONANT_STRING = "BCDFGHJKLMNPQRSTVWXYZ";

    /** TODO DOCUMENT ME */
    private String string;

    /** TODO DOCUMENT ME */
    private int position = -1;
    
    /** TODO DOCUMENT ME */
    private boolean found = false;

    /** TODO DOCUMENT ME */
    private char[] registeredVowels = new char[5];
    
    /**
     * TODO DOCUMENT ME
     * @param string
     */
    public StreamVowel(String string) {
        this.string = string;
    }
    
    /* (non-Javadoc)
     * @see br.com.slzvieira.streamvowel.Stream#getNext()
     */
    public char getNext() {
        if (hasNext()) {
            char next = string.charAt(position);
            found = false;
            return next;
        } else {
            throw new ArrayIndexOutOfBoundsException(position);
        }
    }

    /* (non-Javadoc)
     * @see br.com.slzvieira.streamvowel.Stream#hasNext()
     */
    public boolean hasNext() {
        
        if (found) {
            return true;
        }

        for (;;) {
            
            position++;
            
            /* Se ultrapassou o tamanho o string, retorna false. */
            if (position >= string.length()) {
                return false;
            }

            /* obtem a letra da vez. */
            char letter = string.charAt(position);

            /* Se nao for vogal, ignora. */
            if (!isVowel(letter)) {
                continue;
            }
            
            /* Se a vogal ja foi previamente localizada, ignora. */
            if (isRegistered(letter)) {
                continue;
            }

            /* Para as duas primeiras posicoes, registra a vogal e ignora. */
            if (position < 2) {
                register(letter);
                continue;
            }

            /* Avalia a sequencia de 3 caracteres */
            char firstLetter = string.charAt(position - 2);
            char middleLetter = string.charAt(position - 1);
            char lastLetter = string.charAt(position);
            
            register(letter);

            if (isVowel(firstLetter) && isConsonant(middleLetter) && isVowel(lastLetter)) {
                found = true;
                return true;
            }
        }
    }

    private boolean isVowel(char c) {
        return VOWEL_STRING.indexOf(Character.toUpperCase(c)) > -1;
    }

    private boolean isConsonant(char c) {
        return CONSONANT_STRING.indexOf(Character.toUpperCase(c)) > -1;
    }

    private void register(char letter) {

        for (int i = 0; i < registeredVowels.length;  i++) {

            /* Verifica se ja foi registrada */
            if (registeredVowels[i] == letter) {
                return;
            }

            /* Ocupa a primeira posicao vazia */
            if (registeredVowels[i] == 0) {
                registeredVowels[i] = letter;
                return;
            }
        }
    }

    private boolean isRegistered(char letter) {

        for (char vowel : registeredVowels) {
            if (vowel == letter) {
                return true;
            }
        }

        return false;
    }
}
