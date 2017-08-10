package br.com.slzvieira.streamvowel;

import org.junit.Assert;
import org.junit.Test;

public class StreamVowelTest {

    @Test
    public void test1() {
        Stream stream = new StreamVowel("aAbBABacafe");
        Assert.assertTrue(stream.getNext() == 'e');
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test2() {
        Stream stream = new StreamVowel("The book is on the table");
        stream.getNext();
    }

    @Test
    public void test3() {
        Stream stream = new StreamVowel("abacaxi");
        Assert.assertTrue(stream.getNext() == 'i');
    }

    @Test
    public void test4() {
        Stream stream = new StreamVowel("asofiaspdofiawep");
        Assert.assertTrue(stream.getNext() == 'o');
        Assert.assertTrue(stream.getNext() == 'i');
        Assert.assertTrue(stream.getNext() == 'e');
    }
}
