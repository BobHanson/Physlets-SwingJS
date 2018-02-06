package edu.davidson.tools;

/*
* This package uses the Vigenere Cipher to encode a string of ASCII text.
* The Cipher maps ASCII 32 through ASCII 126 inclusive into a two digit set of
* one letter and one digit (0-3). Other ASCII characters outside ASCII 32
* through ASCII 126 inclusive should not be used. The Vigenere Cipher is
* polyalphabetic. The same character does not always map to the same cipher
* character. The key is composed of 8 numbers in the range of (0, 94). The
* range arises from the span of 94 ASCII characters. Eight is an arbitrary
* choice. This sized key gives a keyspace of 94^8 or about 6 * 10^15 possible
* keys. Take note that key numbers of either 0 or 94 give clear text.
*/


/**
 * This class allows for encryption and decryption of a string as well as
 * specifying a key for the cipher.
 */
public class Crypt {
    private int key[] =  {
        52, 81, 92, 27, 26, 12, 5, 68
    };

    // E N C R Y P T
    public String encrypt (String plaintext) {
        String ciphertext = new String();
        int length = plaintext.length();

        byte byteArr[] = plaintext.getBytes();
        char charArr[] = new char[length];
        int intArr[] = new int[length];

        byte byteArrCiph[] = new byte[2*length];
        char charArrCiph[] = new char[2*length];
        int intArrCiph[] = new int[2*length];

        for (int i = 0; i < length; i++) {
            intArr[i] = (int)byteArr[i] - 32;
        }
        for (int i = 0; i < length; i++) {
            intArr[i] = (((intArr[i] + key[i%8]))%94);
        }
        for (int i = 0; i < length; i++) {
            if (intArr[i] < 0) {
                intArr[i] = intArr[i] + 94;
            }
        }
        for (int i = 0; i < length; i++) {
            intArrCiph[2*i]=(int)(intArr[i]/4)+65; //number of times four goes into numerical representation
            intArrCiph[2*i+1]=(intArr[i]%4)+48;    //numerical representation modulo four
        }
        for (int i = 0; i < 2*length; i++) {
            byteArrCiph[i] = (byte)intArrCiph[i];
        }
        for (int i = 0; i < 2*length; i++) {
            charArrCiph[i] = (char)byteArrCiph[i];
        }
        ciphertext = String.copyValueOf(charArrCiph);
        return  ciphertext;
    }           //end encrypt method

    // D E C R Y P T
    public String decrypt (String ciphertext) {
        String plaintext = new String();
        int length = ciphertext.length();
        length= (int) (length/2);

        byte byteArr[] = ciphertext.getBytes();
        char charArr[] = new char[2*length];
        int intArr[] = new int[2*length];

        byte byteArrDC[] = new byte[length];
        char charArrDC[] = new char[length];
        int intArrDC[] = new int[length];

        for (int i = 0; i < 2*length; i++) {
            intArr[i] = (int)byteArr[i];
        }
        for (int i = 0; i < length; i++) {
            intArrDC[i]=(intArr[2*i]-65)*4;
            intArrDC[i]=intArrDC[i]+intArr[2*i+1]-48;
        }
        for (int i = 0; i < length; i++) {
            intArrDC[i] = (((intArrDC[i] - key[i%8]))%94)+32;
        }
        for (int i = 0; i < length; i++) {
            if (intArrDC[i] < 32)
                intArrDC[i] = intArrDC[i] + 94;
        }
        for (int i = 0; i < length; i++) {
            byteArrDC[i] = (byte)intArrDC[i];
        }
        for (int i = 0; i < length; i++) {
            charArrDC[i] = (char)byteArrDC[i];
        }
        plaintext = String.copyValueOf(charArrDC);
        return  plaintext;
    }           //end decrypt method

    // K E Y
    public void setKey (int inKey[]) {
      this.key = inKey;
      for(int i=0; i<8; i++){
        if(i<inKey.length){
          key[i]= inKey[i] % 94;
        } else key[i]=0;
      }
    }

    // K E Y
    public void setKey (String str) {
      for(int i=0; i<8; i++){
        if(i<str.length()){
          char c=str.charAt(i);
          key[i]=((int)c) % 94;
        } else key[i]=0;
      }
    }
}               //end class


