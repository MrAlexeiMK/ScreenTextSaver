package ru.iot.screentextsaver.repository;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iot.screentextsaver.entity.MessageEntity;

import static java.util.Objects.requireNonNull;

public class MessageRepositoryImplementation implements MessageRepository {
    private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryImplementation.class);

    private final SessionFactory sessionFactory;

    public MessageRepositoryImplementation(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(final MessageEntity message) {
        requireNonNull(message, "message could not be null");
        try (final var session = sessionFactory.openSession()) {
            final var transaction = session.beginTransaction();
            session.persist(message);
            transaction.commit();
            logger.info("Message saved in database: {}.", message);
        } catch (final Exception ex) {
            logger.error("Could not save message in database", ex);
        }
    }
}
