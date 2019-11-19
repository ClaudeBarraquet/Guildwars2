package com.example.claude.guildwars2;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Konynrith on 19/02/2018.
 */

public class Character {

    private String Name;
    private String Profe;
    private String Level;
    private Bitmap Logo;
    private String Race;

    public Character(String Name, String Profe, String Level, Bitmap Logo,String Race){
        this.Name = Name;
        this.Profe = Profe;
        this.Level = Level;
        this.Logo = Logo;
        this.Race = Race;
    }

    public String getName() {

        return Name;
    }

    public void setName(String Name) {

        this.Name = Name;
    }

    public String getProfe() {

        return Profe;
    }

    public void setProfe(String Profe) {

        this.Profe = Profe;
    }

    public String getLevel() {

        return Level;
    }

    public void setLevel(String Level) {

        this.Level = Level;
    }

    public Bitmap getLogo() {

        return Logo;
    }

    public void setLogo(Bitmap Image) {

        this.Logo = Image;
    }

    public String getRace() {

        return Race;
    }

    public void setRace(String Race) {

        this.Race = Race;
    }
}
