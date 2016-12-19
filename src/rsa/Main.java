import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Main {
    private static KeyPair key = null;
   
    public static void main(String[] args) 
    {
       gen();
       byte[] enc = encrypt("witaj świecie", key.getPublic());
       System.out.println(enc);
    }
    
    public static void gen()
    {
        KeyPairGenerator keygen = null;
        try 
        {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) 
        {
            
            e.printStackTrace();
        }
        keygen.initialize(1024);
        key = keygen.genKeyPair();
    }
    
    public static byte [] encrypt(String message, PublicKey pk)
    {
        Cipher cipher = null;
        try 
        {
            cipher  = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            } catch (NoSuchAlgorithmException e) 
            {
                
                e.printStackTrace();
            } catch (NoSuchPaddingException e) 
            {
                
                e.printStackTrace();
            } catch (InvalidKeyException e)
            {
                
                e.printStackTrace();
            }
            byte[] szyfr = null;
            try
            {
                szyfr = cipher.doFinal(message.getBytes());
            }   catch (IllegalBlockSizeException | BadPaddingException e)
            {
            
                e.printStackTrace();
            }
        return szyfr;
    }
    
    public static String decrypt(byte[] szyfr, PrivateKey sk)
    {
        byte[] dec = null;
        Cipher cipher = null;
        try
        {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, sk);
        } catch (NoSuchAlgorithmException el)
        {
        
            el.printStackTrace();
        } catch (NoSuchPaddingException el) 
        {
                
                el.printStackTrace();
        } catch (InvalidKeyException e)
        {
                
                e.printStackTrace();
        }
        
        try
        {
            dec = cipher.doFinal(szyfr);
        } catch (IllegalBlockSizeException | BadPaddingException e)
        {
        
            e.printStackTrace();
        }
        return new String(dec);
    }
}
