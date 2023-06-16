package generator;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.service.OpenAiService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Dalle {
    OpenAiService service;

    public Dalle()  {
        Properties props;
        try (InputStream input = new FileInputStream("config.properties")) {
            props = new Properties();
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String key = System.getenv(props.getProperty("openai.api.key.env"));
        this.service = new OpenAiService(key);
    }

    public String generateImage(String query) {
        String imageSize = "256x256";
        String responseFormat = "b64_json";

        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(query)
                .size(imageSize)
                .responseFormat(responseFormat)
                .build();

        List<Image> images = service.createImage(createImageRequest).getData();

        byte[] imageBytes = Base64.getDecoder().decode(images.get(0).getB64Json());
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);

        BufferedImage image;
        try {
            image = ImageIO.read(bis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            bis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filename = "image_" + UUID.randomUUID() + ".png";
        File outputfile = new File(filename);
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return outputfile.getAbsolutePath();
    }

}
