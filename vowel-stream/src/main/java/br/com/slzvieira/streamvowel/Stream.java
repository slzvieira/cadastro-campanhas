/**
 * 
 */
package br.com.slzvieira.streamvowel;

/**
 * @author slvieira
 */
public interface Stream {

    char getNext();

    boolean hasNext();

    static char firstChar(Stream input) {
        if (input.hasNext()) {
            return input.getNext();
        } else {
            return '\000';
        }
    }
}