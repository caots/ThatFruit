package com.bksoftwarevn.service_impl.home_page;

import com.bksoftwarevn.entities.home_page.ImagePage;
import com.bksoftwarevn.repository.home_page.ImagePageRepository;
import com.bksoftwarevn.service.home_page.ImagePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImagePageService_Impl implements ImagePageService {

    private final static Logger LOGGER = Logger.getLogger(ImagePageService_Impl.class.getName());

    @Autowired
    private ImagePageRepository imagePageRepository;

    @Override
    public List<ImagePage> findAllImage() {
        try {
            return imagePageRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-image-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public ImagePage findById(int id) {
        try {
            ImagePage imagePage = imagePageRepository.findById(id);
            if (imagePage.isStatus()) return imagePage;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-image-page-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveImagePage(ImagePage imagePage) {
        try {
            imagePageRepository.save(imagePage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-image-page-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteImagePage(ImagePage imagePage) {
        try {
            if (imagePage.isStatus()) {
                imagePage.setStatus(false);
                imagePageRepository.save(imagePage);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-image-page-error : {0}", ex.getMessage());
        }
        return false;
    }
}
