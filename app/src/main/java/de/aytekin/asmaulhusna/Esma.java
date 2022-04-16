package de.aytekin.asmaulhusna;

import androidx.annotation.NonNull;

import java.util.Comparator;
import java.util.Random;

public class Esma {
    public enum Type {ARABIC, TRANSKRIPTIONEN, TRANSKRIPTIONTR, MEANINGEN, MEANINGTR, NUMBER, DETAILSTR, AUDIO}

    private final String  transcriptionEN;
    private final String  transcriptionTR;
    private final String  meaningEN;
    private final String  meaningTR;
    private final String  detailsTR;
    private final String  arabic;
    private final int     number;
    private final int     soundID;
    private int right;

    public String getDetailsTR() {
        return detailsTR;
    }

    public int getSoundID() {
        return soundID;
    }

    private int wrong;



    Esma(String transcriptionEN, String transcriptionTR, String meaningEN, String meaningTR, String detailsTR, String arabic, int number, int soundID){
        this.transcriptionEN = transcriptionEN;
        this.transcriptionTR = transcriptionTR;
        this.meaningEN = meaningEN;
        this.meaningTR = meaningTR;
        this.detailsTR = detailsTR;
        this.arabic = arabic;
        this.number = number;
        this.soundID = soundID;
        this.right = 0;
        this.wrong = 0;

    }

    public String getTranscriptionEN() {
        return transcriptionEN;
    }
    public String getTranscriptionTR() {
        return transcriptionTR;
    }

    public String getMeaningEN() {
        return meaningEN;
    }

    public String getMeaningTR() {
        return meaningTR;
    }

    public String getArabic() {
        return arabic;
    }

    public int getNumber() {
        return number;
    }

    public int getSoundResourceInt(){
        return soundID;
    }
    public int getRight() {
        return right;
    }

    public int getWrong() {
        return wrong;
    }

    public int getRating(){
        return getRight()-getWrong();
    }

    public void increaseRight(){
        right++;
    }
    public void increaseWrong(){
        wrong++;
    }

    public boolean isEsmaOf(String txt){
        return transcriptionEN.equals(txt) || transcriptionTR.equals(txt) || meaningEN.equals(txt) || meaningTR.equals(txt) || arabic.equals(txt) || detailsTR.equals(txt);
    }
    public boolean isInRange(){
        if(number >= MainActivity.beginningIndex && number <= MainActivity.endingIndex){
            return true;
        }else {
            return false;
        }
    }

    public boolean isSame(Esma esma){
        return esma.number == this.number;
    }

    public void resetStat(){
        right = 0;
        wrong = 0;
    }
    public String getRandomText(){
        int i = new Random().nextInt(5);

        switch (i){
            case 0:
                return transcriptionEN;
            case 1:
                return transcriptionTR;
            case 2:
                return meaningEN;
            case 3:
                return meaningTR;
            default:
                return arabic;
        }
    }

    public String getTextFromType(Type type){
        switch (type){

            case ARABIC:
                return arabic;
            case TRANSKRIPTIONEN:
                return transcriptionEN;
            case TRANSKRIPTIONTR:
                return transcriptionTR;
            case MEANINGEN:
                return meaningEN;
            case MEANINGTR:
                return meaningTR;
            case NUMBER:
                return "#"+number;
            case AUDIO:
                return "AUDIO";
            default:
                return detailsTR;
        }


    }

    public String getTextExceptType(Type type){
        while(true){
            int i = new Random().nextInt(8);

            Type returnType;
            switch (i){
                case 0:
                    returnType = Type.ARABIC;
                    break;
                case 1:
                    returnType = Type.TRANSKRIPTIONEN;
                    break;
                case 2:
                    returnType = Type.TRANSKRIPTIONTR;
                    break;
                case 3:
                    returnType = Type.MEANINGEN;
                    break;
                case 4:
                    returnType = Type.MEANINGTR;
                    break;
                case 5:
                    returnType = Type.DETAILSTR;
                    break;
                case 6:
                    returnType = Type.NUMBER;
                    break;
                default:
                    returnType = Type.AUDIO;
                    break;
            }

            if(type == Type.TRANSKRIPTIONEN && returnType == Type.TRANSKRIPTIONTR ||
                    type == Type.TRANSKRIPTIONTR && returnType == Type.TRANSKRIPTIONEN ||
                    type == Type.MEANINGEN && returnType == Type.MEANINGTR ||
                    type == Type.MEANINGTR && returnType == Type.MEANINGEN ||
                    ((type == Type.TRANSKRIPTIONEN || type == Type.TRANSKRIPTIONTR) && returnType == Type.AUDIO) ||
                    (type == Type.AUDIO && (returnType == Type.TRANSKRIPTIONEN || returnType == Type.TRANSKRIPTIONTR))
            ){continue;}

            if(returnType != type && isTypeChecked(returnType)){return getTextFromType(returnType);}
        }
    }

    public static boolean isTypeChecked(Type type){
        switch (type){

            case ARABIC:
                return MainActivity.arabic;
            case TRANSKRIPTIONEN:
                return MainActivity.transcriptionEN;
            case TRANSKRIPTIONTR:
                return MainActivity.transcriptionTR;
            case MEANINGEN:
                return MainActivity.meaningEN;
            case MEANINGTR:
                return MainActivity.meaningTR;
            case NUMBER:
                return MainActivity.number;
            case DETAILSTR:
                return MainActivity.detailsTR;
            case AUDIO:
                return MainActivity.soundID;
        }
        return false;
    }

    public boolean hasStats(){
        return getRight() != 0 || getWrong() != 0;
    }

    public int getOgg(){
        switch (number){
            case 1:
                return R.raw._1;
            case 2:
                return R.raw._2;
            case 3:
                return R.raw._3;
            case 4:
                return R.raw._4;
            case 5:
                return R.raw._5;
            case 6:
                return R.raw._6;
            case 7:
                return R.raw._7;
            case 8:
                return R.raw._8;
            case 9:
                return R.raw._9;
            case 10:
                return R.raw._10;
            case 11:
                return R.raw._11;
            case 12:
                return R.raw._12;
            case 13:
                return R.raw._13;
            case 14:
                return R.raw._14;
            case 15:
                return R.raw._15;
            case 16:
                return R.raw._16;
            case 17:
                return R.raw._17;
            case 18:
                return R.raw._18;
            case 19:
                return R.raw._19;
            case 20:
                return R.raw._20;
            case 21:
                return R.raw._21;
            case 22:
                return R.raw._22;
            case 23:
                return R.raw._23;
            case 24:
                return R.raw._24;
            case 25:
                return R.raw._25;
            case 26:
                return R.raw._26;
            case 27:
                return R.raw._27;
            case 28:
                return R.raw._28;
            case 29:
                return R.raw._29;
            case 30:
                return R.raw._30;
            case 31:
                return R.raw._31;
            case 32:
                return R.raw._32;
            case 33:
                return R.raw._33;
            case 34:
                return R.raw._34;
            case 35:
                return R.raw._35;
            case 36:
                return R.raw._36;
            case 37:
                return R.raw._37;
            case 38:
                return R.raw._38;
            case 39:
                return R.raw._39;
            case 40:
                return R.raw._40;
            case 41:
                return R.raw._41;
            case 42:
                return R.raw._42;
            case 43:
                return R.raw._43;
            case 44:
                return R.raw._44;
            case 45:
                return R.raw._45;
            case 46:
                return R.raw._46;
            case 47:
                return R.raw._47;
            case 48:
                return R.raw._48;
            case 49:
                return R.raw._49;
            case 50:
                return R.raw._50;
            case 51:
                return R.raw._51;
            case 52:
                return R.raw._52;
            case 53:
                return R.raw._53;
            case 54:
                return R.raw._54;
            case 55:
                return R.raw._55;
            case 56:
                return R.raw._56;
            case 57:
                return R.raw._57;
            case 58:
                return R.raw._58;
            case 59:
                return R.raw._59;
            case 60:
                return R.raw._60;
            case 61:
                return R.raw._61;
            case 62:
                return R.raw._62;
            case 63:
                return R.raw._63;
            case 64:
                return R.raw._64;
            case 65:
                return R.raw._65;
            case 66:
                return R.raw._66;
            case 67:
                return R.raw._67;
            case 68:
                return R.raw._68;
            case 69:
                return R.raw._69;
            case 70:
                return R.raw._70;
            case 71:
                return R.raw._71;
            case 72:
                return R.raw._72;
            case 73:
                return R.raw._73;
            case 74:
                return R.raw._74;
            case 75:
                return R.raw._75;
            case 76:
                return R.raw._76;
            case 77:
                return R.raw._77;
            case 78:
                return R.raw._78;
            case 79:
                return R.raw._79;
            case 80:
                return R.raw._80;
            case 81:
                return R.raw._81;
            case 82:
                return R.raw._82;
            case 83:
                return R.raw._83;
            case 84:
                return R.raw._84;
            case 85:
                return R.raw._85;
            case 86:
                return R.raw._86;
            case 87:
                return R.raw._87;
            case 88:
                return R.raw._88;
            case 89:
                return R.raw._89;
            case 90:
                return R.raw._90;
            case 91:
                return R.raw._91;
            case 92:
                return R.raw._92;
            case 93:
                return R.raw._93;
            case 94:
                return R.raw._94;
            case 95:
                return R.raw._95;
            case 96:
                return R.raw._96;
            case 97:
                return R.raw._97;
            case 98:
                return R.raw._98;
            default:
                return R.raw._99;
        }
    }
}


