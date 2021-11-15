package com.example.isiahlibor.quizvote;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Isiah Libor on 3/7/2018.
 */

public class list implements Serializable {

    private ArrayList<String> pres;

    public ArrayList<String> getPres()
    {
        return pres;
    }
    public void setPres(ArrayList<String> pres)
    {
        this.pres = pres;
    }

    private ArrayList<String> vice;

    public ArrayList<String> getVice()
    {
        return vice;
    }
    public void setVice(ArrayList<String> vice)
    {
        this.vice = vice;
    }

    private ArrayList<String> sec;

    public ArrayList<String> getSec()
    {
        return sec;
    }
    public void setSec(ArrayList<String> sec)
    {
        this.sec = sec;
    }

    private ArrayList<String> trea;

    public ArrayList<String> getTrea()
    {
        return trea;
    }
    public void setTrea(ArrayList<String> trea)
    {
        this.trea = trea;
    }
}
