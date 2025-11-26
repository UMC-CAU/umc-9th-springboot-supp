package Repository;

import Dto.QReviewResponse;
import Dto.ReviewCondition;
import Dto.ReviewResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import Entity.QReview;
import Entity.QStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    QReview review = QReview.review;
    QStore store = QStore.store;

    @Override
    public Page<ReviewResponse> findMyReviews(Long memberId, ReviewCondition condition, Pageable pageable) {
        List<ReviewResponse> content= queryFactory
                .select(new QReviewResponse(review.review_id, review.rating, review.content, review.createdAt))
                .from(review)
                .join(review.store, store)
                .where(review.member.id.eq(memberId), storeIdEq(condition.getStoreId()))
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(review.member.id.eq(memberId), storeIdEq(condition.getStoreId()), ratingEq(condition.getRating()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }
    private BooleanExpression storeIdEq(Long storeId) {
        return storeId == null ? null : review.store.store_id.eq(storeId);
    }//내부에 null확인을 넣는건 아닌것같았다.
    private BooleanExpression ratingEq(Double rating) {
        return rating == null ? null : review.rating.eq(rating);
    }

}

