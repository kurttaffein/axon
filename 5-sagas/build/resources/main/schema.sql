create table DomainEventEntry ( 
                        aggregateIdentifier varchar(255) not null, 
                        sequenceNumber bigint not null, 
                        type varchar(255) not null, 
                        eventIdentifier varchar(255) not null, 
                        metaData blob, 
                        payload blob not null, 
                        payloadRevision varchar(255), 
                        payloadType varchar(255) not null, 
                        timeStamp varchar(255) not null, 
                        primary key (aggregateIdentifier, sequenceNumber, type) 
                    );

create table SnapshotEventEntry ( 
                        aggregateIdentifier varchar(255) not null,
                        sequenceNumber bigint not null,
                        type varchar(255) not null,
                        eventIdentifier varchar(255) not null,
                        metaData blob,
                        payload blob not null,
                        payloadRevision varchar(255),
                        payloadType varchar(255) not null,
                        timeStamp varchar(255) not null,
                        primary key (aggregateIdentifier, sequenceNumber, type)
                    );

create table AssociationValueEntry ( 
                        id int not null,
                        associationKey varchar(255), 
                        associationValue varchar(255), 
                        sagaId varchar(255), 
                        sagaType varchar(255), 
                        primary key (id) 
                    );

create table SagaEntry ( 
                        sagaId varchar(255) not null, 
                        revision varchar(255), 
                        sagaType varchar(255), 
                        serializedSaga blob, 
                        primary key (sagaId) 
                    );