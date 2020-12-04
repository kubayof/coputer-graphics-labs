package com.naofi.lab4.servlet;

import com.naofi.lab4.images.BarnsleyFern;
import com.naofi.lab4.images.Cloud;
import com.naofi.lab4.images.Snowflake;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageServlet extends HttpServlet {
    private BufferedImage snowflakeImage;
    private BufferedImage cloudImage;
    private BufferedImage fernImage;


    @Override
    public void init() throws ServletException {
        super.init();
        snowflakeImage = new Snowflake(5).getImage();
        cloudImage = new Cloud().getImage();
        fernImage = new BarnsleyFern().getImage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        BufferedImage image;
        switch (type) {
            case "snowflake":
                image = snowflakeImage;
                break;
            case "cloud":
                image = cloudImage;
                break;
            case "fern":
                image = fernImage;
                break;
            default:
                throw new IllegalArgumentException("Unknown image type: " + type);
        }

        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());
    }
}