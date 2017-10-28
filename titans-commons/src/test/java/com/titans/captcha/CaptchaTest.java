package com.titans.captcha;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.RandomYBestFitTextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author vinfai
 * @since 2017/6/21
 */
public class CaptchaTest {

    public static void main(String[] args) throws Exception{
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        try {
            FileOutputStream fos = new FileOutputStream("e:/patcha_img.png");
            EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doCaptcha2();
    }

    public static final String defaultCapChar = "ABCDEFGHKLMNPRSTUVXYZacdefhkmnprstuwvxz345789";

    public static void doCaptcha2() throws Exception{
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        RandomWordFactory wordFactory = new RandomWordFactory();//字母范围及数量
        wordFactory.setCharacters(defaultCapChar);
        wordFactory.setMaxLength(4);
        wordFactory.setMinLength(4);
        cs.setWordFactory(wordFactory);
        cs.setTextRenderer(new RandomYBestFitTextRenderer());
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));

        cs.setWidth(80);
        cs.setHeight(40);
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(30);
        fontFactory.setMinSize(30);
        cs.setFontFactory(fontFactory);

        cs.setFilterFactory(new DoubleRippleFilterFactory());

       /* Captcha captcha = cs.getCaptcha();
        BufferedImage image = captcha.getImage();*/

       /* try {
            FileOutputStream fos = new FileOutputStream("e:/patcha_img2.png");
            //String png = EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
            Captcha captcha = cs.getCaptcha();
            String challenge = captcha.getChallenge();
            ImageIO.write(captcha.getImage(), "png", fos);
            System.out.println(challenge);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        BufferedImage bufImage = new BufferedImage(80, 40, BufferedImage.TYPE_INT_ARGB_PRE);
        cs.getBackgroundFactory().fillBackground(bufImage);
        String word = wordFactory.getNextWord();
        cs.getTextRenderer().draw(word, bufImage, fontFactory, cs.getColorFactory());
        bufImage = cs.getFilterFactory().applyFilters(bufImage);
        FileOutputStream fos = new FileOutputStream("e:/patcha_img2.png");
        ImageIO.write(bufImage, "png", fos);

    }
}
