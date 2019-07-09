    
    package services;

    import domain.CreditCard;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.CreditCardRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class CreditCardService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private CreditCardRepository        creditcardRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public CreditCardService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public CreditCard create(){
            CreditCard result;
            result = new CreditCard();
            return result;
        }
        
        public Collection<CreditCard> findAll() {
            Collection<CreditCard> result;

            result = creditcardRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public CreditCard findOne(int creditcardId) {
            Assert.isTrue(creditcardId != 0);

            CreditCard result;

            result = creditcardRepository.findOne(creditcardId);
            Assert.notNull(result);

            return result;
        }

        public CreditCard save(CreditCard creditcard) {
            Assert.notNull(creditcard);

            CreditCard result;

            result = creditcardRepository.save(creditcard);

            return result;
        }

        public void delete(CreditCard creditcard) {
            Assert.notNull(creditcard);
            Assert.isTrue(creditcard.getId() != 0);
            Assert.isTrue(creditcardRepository.exists(creditcard.getId()));

            creditcardRepository.delete(creditcard);
        }

        // Other business methods -------------------------------------------------


    }

    