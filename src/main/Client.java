package main;

public class Client {

    public static void main(String[] args) {
        String ogUrl = "https://localhost/users/v1/projects";

        URLshortenerService urlService = new URLshortenerService();
        String encodedUrl = urlService.encode(ogUrl);
        System.out.println("Encoded URL->"+encodedUrl);
        String decodedUrl = urlService.decode(encodedUrl);
        System.out.println("Decoded URL->"+decodedUrl);
    }
}
