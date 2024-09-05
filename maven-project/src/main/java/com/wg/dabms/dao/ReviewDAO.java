package com.wg.dabms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.wg.dabms.model.Review;

public class ReviewDAO extends GenericDAO<Review> {

    @Override
    protected Review mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setUuid(resultSet.getString("uuid"));
        review.setRevieweeId(resultSet.getString("reviewee_id"));
        review.setReviewerId(resultSet.getString("reviewer_id"));
        review.setDescription(resultSet.getString("description"));
        review.setRating(resultSet.getBigDecimal("rating"));
        review.setReported(resultSet.getBoolean("is_reported"));
        review.setHidden(resultSet.getBoolean("is_hidden"));
        review.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        review.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return review;
    }

    public boolean createReview(Review review) throws SQLException {
        String query = "INSERT INTO Review (uuid, reviewee_id, reviewer_id, description, rating, is_reported, is_hidden, created_at, updated_at) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, review.getUuid(), review.getRevieweeId(), review.getReviewerId(), review.getDescription(),
                             review.getRating(), review.isReported(), review.isHidden(),
                             java.sql.Timestamp.valueOf(review.getCreatedAt()), java.sql.Timestamp.valueOf(review.getUpdatedAt()));
    }

    public boolean updateReview(Review review) throws SQLException {
        String query = "UPDATE Review SET reviewee_id = ?, reviewer_id = ?, description = ?, rating = ?, is_reported = ?, is_hidden = ?, updated_at = ? WHERE uuid = ?";
        return executeUpdate(query, review.getRevieweeId(), review.getReviewerId(), review.getDescription(),
                             review.getRating(), review.isReported(), review.isHidden(),
                             java.sql.Timestamp.valueOf(review.getUpdatedAt()), review.getUuid());
    }

    public boolean deleteReview(String uuid) throws SQLException {
        String query = "DELETE FROM Review WHERE uuid = ?";
        return executeDelete(query, uuid);
    }

    public Review getReviewById(String uuid) throws SQLException {
        String query = "SELECT * FROM Review WHERE uuid = ?";
        return executeGetQuery(query, uuid);
    }

    public List<Review> getAllReviews() throws SQLException {
        String query = "SELECT * FROM Review";
        return executeGetAllQuery(query);
    }
    
    public List<Review> findByUserId(String userId) throws SQLException {
        String query = "SELECT * FROM Review WHERE reviewee_id = ? OR reviewer_id = ?";
        return executeGetAllQuery(query);
    }
}
