package com.wg.dabms.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dabms.dao.ReviewDAO;
import com.wg.dabms.model.Review;

public class ReviewService {

    private ReviewDAO reviewDAO;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
    }

    public void createReview(Review review) {
        try {
            reviewDAO.createReview(review);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> findReviewsByUserId(String userId) {
        try {
            return reviewDAO.findByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
