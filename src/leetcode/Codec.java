package leetcode;

import java.math.BigInteger;
        import java.util.HashMap;
        import java.util.Map;

public class Codec {

    private static final String prefix = "http://tinyurl.com/";
    private static final BigInteger sequence = BigInteger.ONE;
    private Map<String, String> urlMap = new HashMap<>();

    public String encode(String longUrl) {
        BigInteger next = sequence.add(BigInteger.ONE);
        urlMap.put(next.toString(), longUrl);

        return next.toString();
    }

    public String decode(String shortUrl) {
        return urlMap.get(shortUrl);
    }
}
