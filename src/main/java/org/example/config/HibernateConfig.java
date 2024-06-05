package org.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {
      private static StandardServiceRegistry registry;
      private static SessionFactory sessionfactory;

      public static SessionFactory getSessionfactory()
      {
            if(sessionfactory==null)
            {
                  try {
                        registry = new StandardServiceRegistryBuilder().configure().build();
                        MetadataSources sources = new MetadataSources(registry);
                        Metadata metadata = sources.getMetadataBuilder().build();
                        sessionfactory = metadata.getSessionFactoryBuilder().build();
                              }
                  catch(Exception e)
                  {
                       e.printStackTrace();
                       if(sessionfactory!=null)
                       {
                             StandardServiceRegistryBuilder.destroy(registry);

                              }
                        }
                  }
            return sessionfactory;
            }
            public static void shutdown()
            {
                  if(registry!=null)
                  {
                        StandardServiceRegistryBuilder.destroy(registry);
                  }
      }
}
