
class tGrade {

public double equivalent(double value) {
double initial = value;
	if(initial==100){
        return 100;
        }
    else if(initial>=98.40 && initial<= 99.99){
        return 99;
    }
    else if(initial>=96.80 && initial<= 98.39){
        return 98;
    }
    else if(initial>=95.20 && initial<= 96.79){
        return 97;
    }
    else if(initial>=93.60 && initial<= 95.19){
        return 96;
    }
    else if(initial>=92.00 && initial<= 93.59){
        return 95;
    }
    else if(initial>=90.40 && initial<= 91.99){
        return 94;
    }
    else if(initial>=88.80 && initial<= 90.39){
        return 93;
    }
    else if(initial>=87.20 && initial<= 88.79){
        return 92;
    }
    else if(initial>=85.60 && initial<= 87.19){
        return 91;
    }
    else if(initial>=84 && initial<= 85.59){
        return 90;
    }
    else if(initial>=82.40 && initial<= 83.99){
        return 89;
    }
    else if(initial>=80.80 && initial<= 82.39){
        return 88;
    }
    else if(initial>=79.20 && initial<= 80.79){
        return 87;
    }
    else if(initial>=77.60 && initial<= 79.19){
        return 86;
    }
    else if(initial>=76 && initial<= 77.59){
        return 85;
    }
    else if(initial>=74.40 && initial<= 75.99){
        return 84;
    }
    else if(initial>=72.80 && initial<= 74.39){
        return 83;
    }
    else if(initial>=71.20 && initial<= 72.79){
        return 82;
    }
    else if(initial>=69.60 && initial<= 71.19){
        return 81;
    }
    else if(initial>=68.00 && initial<= 69.59){
        return 80;
    }
    else if(initial>=66.40 && initial<= 67.99){
        return 79;
    }
    else if(initial>=64.80 && initial<= 66.39){
        return 78;
    }
    else if(initial>=63.20 && initial<= 64.79){
        return 77;
    }
    else if(initial>=61.60 && initial<= 63.19){
        return 76;
    }
    else if(initial>=60 && initial<= 61.59){
        return 75;
    }
    else if(initial>=56 && initial<= 59.99){
        return 74;
    }
    else if(initial>=52 && initial<= 55.99){
        return 73;
    }
    else if(initial>=48 && initial<= 51.99){
        return 72;
    }
    else if(initial>=44 && initial<= 47.99){
        return 71;
    }
    else if(initial>=40 && initial<= 43.99){
        return 70;
    }
    else if(initial>=36 && initial<= 39.99){
        return 69;
    }
    else if(initial>=32 && initial<= 35.99){
        return 68;
    }
    else if(initial>=28 && initial<= 31.99){
        return 67;
    }
    else if(initial>=24 && initial<= 27.99){
        return 66;
    }
    else if(initial>=20 && initial<= 23.99){
        return 65;
    }
    else if(initial>=16 && initial<= 19.99){
        return 64;
    }
    else if(initial>=12 && initial<= 15.99){
        return 63;
    }
    else if(initial>=8 && initial<= 11.99){
        return 62;
    }
    else if(initial>=4 && initial<= 7.99){
        return 61;
    }
    else {
        return 60;
    }
}
}
