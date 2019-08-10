package com.bksoftwarevn.service.home_page;

import com.bksoftwarevn.entities.home_page.ImagePage;

import java.util.List;

public interface ImagePageService {

    List<ImagePage> findAllImage();

    List<ImagePage> findAllImageBetweenId(int startId, int endId);

    ImagePage findById(int id);

    boolean saveImagePage(ImagePage imagePage);

    boolean deleteImagePage(ImagePage imagePage);
}
