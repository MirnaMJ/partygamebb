package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class CustomUiBdf {
    public Bombdife game;
    private TextButton.TextButtonStyle textButtonStyle0;
    private TextButton.TextButtonStyle textButtonStyle1;

    private ImageButton.ImageButtonStyle ButtonStyle0;
    private ImageButton.ImageButtonStyle ButtonStyle1;
    private ImageButton.ImageButtonStyle ButtonStyle2;
    private ImageButton.ImageButtonStyle ButtonStyle3;

    private Label.LabelStyle labelStyle0;
    private Label.LabelStyle labelStyle1;
    private Label.LabelStyle labelStyle2;

    private TextField.TextFieldStyle textFStyle;

    private Slider.SliderStyle sliderStyle;

    private Drawable tableStyle0;
    private Drawable tableStyle1;

    private Skin skin;
    private TextureAtlas bombdife;
    private Table table;
    private TextButton tButton;
    private ImageButton button;
    private Label label;
    private TextField textField;
    private Slider slider;
    private Color tintColor0;
    private Color tintColor1;
    private Color tintColor2;
    private Color tintColor3;



    public CustomUiBdf(Bombdife game) {
        this.game = game;
        bombdife = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));

        skin = new Skin();
        skin.addRegions(bombdife);

        textButtonStyle0 = new TextButton.TextButtonStyle();
        textButtonStyle0.font = game.getFont20();
        textButtonStyle0.up = skin.newDrawable("noback_rec");
        tintColor0 = new Color(0f, 1f, 1f, 1f);
        textButtonStyle0.down = skin.newDrawable("noback_rec",tintColor0);

        textButtonStyle1 = new TextButton.TextButtonStyle();
        textButtonStyle1.font = game.getFont20();
        textButtonStyle1.up = skin.getDrawable("back_rec");
        textButtonStyle1.down = skin.getDrawable("back_rec");


        ButtonStyle0 = new ImageButton.ImageButtonStyle();
        ButtonStyle0.up = skin.getDrawable("arrow");
        ButtonStyle0.down = skin.getDrawable("arrow");

        ButtonStyle1 = new ImageButton.ImageButtonStyle();
        tintColor1 = new Color(0.2f, 1f, 1f, 1f);
        ButtonStyle1.up = skin.newDrawable("retry");
        ButtonStyle1.down = skin.newDrawable("retry",tintColor1);

        ButtonStyle2 = new ImageButton.ImageButtonStyle();
        tintColor2 = new Color(1f, 1f, 1f, 1f);
        ButtonStyle2.up = skin.newDrawable("back_rec");
        ButtonStyle2.down = skin.newDrawable("noback_rec",tintColor2);

        ButtonStyle3 = new ImageButton.ImageButtonStyle();
        ButtonStyle3.up = skin.newDrawable("arrow_r");
        ButtonStyle3.down = skin.newDrawable("arrow_r",tintColor2);


        labelStyle0  = new Label.LabelStyle();
        labelStyle0.font = game.getFont20();
        labelStyle1 = new Label.LabelStyle();
        labelStyle1.font = game.getFont40();
        labelStyle2 = new Label.LabelStyle();
        labelStyle2.font = game.getFont80();

        textFStyle = new TextField.TextFieldStyle();
        textFStyle.font = game.getFont20();
        textFStyle.fontColor = new Color(0.81f, 0.81f, 0.81f, 1f);
        textFStyle.background = skin.getDrawable("field");
        textFStyle.cursor =  skin.getDrawable("cursor");
        textFStyle.selection = skin.getDrawable("noback_rec");

        sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skin.getDrawable("bar");
        sliderStyle.knob = skin.getDrawable("iconbomb");

        tintColor3 = new Color(0.5f, 1f, 1f, 1f);
        tableStyle0 = skin.newDrawable("field",tintColor3);
        tableStyle1 = skin.getDrawable("field");


    }


    public TextButton createTButton(String text, String style) {
        switch (style) {
            case "noback":
                tButton = new TextButton(text, textButtonStyle0);
                return tButton;
            case "back":
                tButton = new TextButton(text, textButtonStyle1);
                return tButton;
            default:
                //System.out.println("no button style?");
                tButton = new TextButton(text, textButtonStyle0);
                break;
        }
        return tButton;
    }

    public ImageButton createButton(String style){
        switch (style){
            case "arrow":
                button = new ImageButton(ButtonStyle0);
                return button;
            case "arrow_r":
                button = new ImageButton(ButtonStyle3);
                return button;
            case "retry":
                button = new ImageButton(ButtonStyle1);
                return button;
            case "back":
                button = new ImageButton(ButtonStyle2);
                return button;
            default:
                button = new ImageButton(ButtonStyle0);
                break;
        }
        return button;
    }

    public Label createLabel(int font, String txt){
        switch(font){
            case 20:
                label = new Label(txt,labelStyle0);
                return label;
            case 40:
                label = new Label(txt,labelStyle1);
                return label;
            case 80:
                label = new Label(txt,labelStyle2);
                return label;
            default:
                label = new Label(txt,labelStyle0);
                break;

        }
        return label;
    }

    public TextField createTField(String txt, String style){
        textField = new TextField("",textFStyle);
        return textField;
    }

    public Slider createSlider(int min, int max, float step, boolean vertical, String style){
        slider = new Slider(min, max, step, vertical, sliderStyle );
        return slider;
    }

    public Table createTable(String rowType){
        switch (rowType){
            case "header":
                table = new Table(skin);
                table.setBackground(tableStyle0);
                return table;
            case "basic":
                table = new Table(skin);
                table.setBackground(tableStyle1);
                return table;
            default:
                table = new Table(skin);
                table.setBackground(tableStyle1);
                break;
        }
        return table;
    }

}
