package _01_DESAndAES;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @Classname DESAndAESDemo
 * @Description TODO
 * @Date 2020/6/10 11:59
 * @Created by Harman
 */
public class DESAndAESDemo {
    //定义加密模式
    private final static String DES = "DES";
    //定义密钥(DES的密钥必须为8字节)
    private final static String DEFAULT_KEY = "12345678";
    //设置编码
    private final static String ENCODING = "UTF-8";

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        //定义测试字段
        String s_Data = "测试字段";
        //加密
        String s_EnCryptography = enCryptography(s_Data.getBytes(ENCODING));
        System.out.println("加密:" + s_EnCryptography);
        //解密
        System.out.println("解密:" + deCryptography(s_EnCryptography));
    }

    //加密
    private static String enCryptography(byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        //生成一个可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        //将默认密钥生成一个DESKeySpace对象
        DESKeySpec desKeySpec = new DESKeySpec(DEFAULT_KEY.getBytes(ENCODING));
        //创建一个密钥工厂，将DESKeySpace转换为SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        //Cipher对象完成实际加密操作
        Cipher cipher = Cipher.getInstance(DES);
        //用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,secureRandom);
        //完成操作
        byte[] bytes = cipher.doFinal(data);
        return new String(new BASE64Encoder().encode(bytes));
    }

    //解密
    private static String deCryptography(String s_Data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, IOException {
        //生成一个可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        //将默认密钥生成一个DESKeySpace对象
        DESKeySpec desKeySpec = new DESKeySpec(DEFAULT_KEY.getBytes(ENCODING));
        //创建一个密钥工厂，将DESKeySpace转换为SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        //Cipher对象完成实际加密操作
        Cipher cipher = Cipher.getInstance(DES);
        //用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE,secretKey,secureRandom);
        //完成操作
        byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(s_Data));
        return new String(bytes,ENCODING);
    }

}
