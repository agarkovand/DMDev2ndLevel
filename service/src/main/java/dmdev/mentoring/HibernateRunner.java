package dmdev.mentoring;

import dmdev.mentoring.entity.City;
import dmdev.mentoring.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        cfg.configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            Stadium suzukiArena = Stadium.builder()
//                    .name("Suzuki Arena")
//                    .cityId(1L)
//                    .build();
//            City cityKielce = City.builder()
//                    .name("Kielce")
//                    .region("Świętokrzyskie")
//                    .country(Country.POLAND)
//                    .build();
//            session.persist(suzukiArena);
//            session.persist(cityKielce);

            City kielce = City.builder()
                    .name("Kielce")
                    .region("Świętokrzyskie województwo")
                    .country(Country.POLAND)
                    .build();
            session.persist(kielce);
            session.getTransaction().commit();
        }
    }
}