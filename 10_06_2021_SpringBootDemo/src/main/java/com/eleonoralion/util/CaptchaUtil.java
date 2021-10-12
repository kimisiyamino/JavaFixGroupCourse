package com.eleonoralion.util;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.noise.StraightLineNoiseProducer;
import cn.apiclub.captcha.text.producer.ArabicTextProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.producer.FiveLetterFirstNameTextProducer;
import cn.apiclub.captcha.text.renderer.ColoredEdgesWordRenderer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

public class CaptchaUtil {

    //Creating Captcha Object
    public static Captcha createCaptcha(Integer width, Integer height) {
        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer(new Color(
                                                                       new Random().nextInt(255),
                                                                       new Random().nextInt(255),
                                                                       new Random().nextInt(255)),
                                                               new Color(
                                                                       new Random().nextInt(255),
                                                                       new Random().nextInt(255),
                                                                       new Random().nextInt(255))))
                .addText(new DefaultTextProducer(), new ColoredEdgesWordRenderer())
                .addNoise(new StraightLineNoiseProducer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    //Converting to binary String
    public static String encodeCaptcha(Captcha captcha) {
        String image = null;
        try {
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg", bos);
            byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
            image = new String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
