package smart.BackEnd;

import java.util.Random;

public class GenerateRandom {
    public String generateAlphaNumerical(int n) {
        int leftLimit = 48; // 0
        int rightLimit = 122; // letter 'z'
        int targetStringLength = n;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (randomLimitedInt < 48 || (randomLimitedInt > 57 && randomLimitedInt < 65) || (randomLimitedInt > 90 && randomLimitedInt < 97)) {
                i--;
                continue;
            }
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public String generateNumerical(int n) {
        int leftLimit = 48; //0
        int rightLimit = 57; // 9
        int targetStringLength = n;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (randomLimitedInt < 48 || (randomLimitedInt > 57 && randomLimitedInt < 65) || (randomLimitedInt > 90 && randomLimitedInt < 97)) {
                i--;
                continue;
            }
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
}
