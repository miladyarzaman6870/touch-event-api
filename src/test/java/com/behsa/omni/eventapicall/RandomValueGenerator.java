package com.behsa.omni.eventapicall;



import com.behsa.omni.eventapicall.domain.InputModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomValueGenerator {
    static Random random = new Random();
    public static String rndString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
    public static Integer randomInt(int max) {
        return random.nextInt(max);
    }
    public static int randomIntBetwenAandB(int max, int min) {
        return (int) (min + (max - min) * Math.random());
    }
    public static String rndValidTelNumber() {
        int targetStringLength=12;
        // int leftLimit = 97; // letter 'a'
        // int rightLimit = 122; // letter 'z'
        //  int rightLimit = 122; // letter 'A'
//        int rightLimit = 122; // letter 'Z'
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        buffer.append("989");
        for (int i = 3; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static String rndBillIdAndPayId(int targetStringLength) {

        // int leftLimit = 97; // letter 'a'
        // int rightLimit = 122; // letter 'z'
        //  int rightLimit = 122; // letter 'A'
//        int rightLimit = 122; // letter 'Z'
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static String rndValidPin() {
        int targetStringLength=12;
        // int leftLimit = 97; // letter 'a'
        // int rightLimit = 122; // letter 'z'
        //  int rightLimit = 122; // letter 'A'
//        int rightLimit = 122; // letter 'Z'
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static String rndInValidPin() {
        //validPin can not contains leter
        int targetStringLength=12;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        //  int rightLimit = 65; // letter 'A'
//        int rightLimit = 90; // letter 'Z'
        //  int leftLimit = 48; // letter '0'
        //int rightLimit = 57; // letter '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static String rndInValidTelNumber() {
        int targetStringLength=12;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        //  int rightLimit = 122; // letter 'A'
//        int rightLimit = 122; // letter 'Z'
        //int leftLimit = 48; // letter '0'
        // int rightLimit = 57; // letter '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        buffer.append("989");
        for (int i = 3; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
    public static int rndValueBetweenMaxMin(int max , int min){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
    public static Long rndValueBetweenMaxMin(long max , int min){
        Long random_int = (long)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
    public static int rndValidPaymentAmount(int max , int min){
        int random_int;
        do {
            random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        }while (!(random_int%100==0 && random_int<=max && random_int>=min));
        return random_int;
    }
    public static int rndInValidPaymentAmount(int max , int min){
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;

    }
    public static Long randomIntBetwenAandB(Long max, int min){
        return (long) ( min + (max - min) * Math.random());
    }
    public static Long randomIntBetwenAandB(Long max, long min){
        return (long) ( min + (max - min) * Math.random());
    }
    //for example payid=543862735438  then amount must  amount=5438627000
    public static String[] randomValidPayIdAndAmount(){
        int targetStringLength=14;
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 3; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return new String[]{buffer.toString(),(buffer.substring(0,buffer.toString().length()-5)+"000")};

    }
    public static String[] randomInValidPayIdAndAmount(){

        return new String[]{randomInt(10000000).toString(),randomInt(10000000).toString()};

    }
    public static String randomValidBillId(){
        List<String> billIds=new ArrayList<>();
        billIds.add("507857");
        billIds.add("1405237005221");
        billIds.add("486695504128");
        billIds.add("1307733505429");
        return billIds.get(randomInt(billIds.size()-1));
    }
    public static String randomInValidBillId(){
        List<String> billIds=new ArrayList<>();
        billIds.add("5078575846688555255555");
        billIds.add("140523700522174895762154367472");
        billIds.add("486");
        billIds.add("1307733505429kjhygbnmjhgk");
        return billIds.get(randomInt(billIds.size()-1));
    }
    public static String randomInValidBillIdwithChar(){
        List<String> billIds=new ArrayList<>();
        billIds.add("50d785");
        billIds.add("140v523700522");
        billIds.add("486695504b12");
        billIds.add("13077335e0542");
        return billIds.get(randomInt(billIds.size()-1));
    }
    public static String randomValidNationalId(){
        List<String>  strings=new ArrayList<>();
        Random random=new Random();
        strings.add("5810002153");
        strings.add("0024080861");
        strings.add("4520028043");
        return strings.get(random.nextInt(strings.size()));
    }
    public static String randomInValidNationalId(){
        List<String>  strings=new ArrayList<>();
        Random random=new Random();
        strings.add("0000000000");
        strings.add("1111111111");
        strings.add("2222222222");
        strings.add("3333333333");
        strings.add("4444444444");
        strings.add("5555555555");
        strings.add("6666666666");
        strings.add("7777777777");
        strings.add("8888888888");
        strings.add("9999999999");
        strings.add("45200280");
        strings.add("452002804");
        strings.add("45200280431");
        strings.add("4520028043a");
        strings.add("452002804a");
        strings.add("4520028043");
        strings.add("4520028043");
        return strings.get(random.nextInt(strings.size()));
    }
    public static InputModel getRandomValidInput(){
        InputModel inputModel=new InputModel();
        inputModel.setBody(rndString(12));
        inputModel.setToken(rndString(12));
        inputModel.setUrl("http://localhost:");
        return inputModel;
    }

}
