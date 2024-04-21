package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.PersonEntity;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return Added person with [id]
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    PersonDTO getPersonById(long personId);

    /**
     * Hides existing person and creates a new person with updated data
     *
     * @param personId Person to update
     * @param personDTO Updated data of the person
     * @return New person
     */
    PersonDTO updatePerson(long personId, PersonDTO personDTO);


    /**
     * Fetches a person with the passed [id]
     *
     * @param personId Person to fetch
     * @return Fetched person
     */
    PersonEntity fetchPersonById(long personId);

    List<PersonStatisticsDTO> getPersonsStatistics();
}
