import { useLocation, Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState, useContext } from "react";
import AuthContext from "../context/AuthContext"
import Errors from './Errors';


export default function Detail() {

    //passing prop via state from View Link
    const stateLocation = useLocation();
    //const {from } = stateLocation.state;
  //  console.log(stateLocation);


    const [activityData, setActivityDetails] = useState([]);
    const [errors, setErrors] = useState([]);

    const history = useNavigate();
    const [userStatus, setUserStatus] = useContext(AuthContext);

    const [activityName, setActivityName] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [userId, setUserId] = useState('');
    const [maxParticipant, setMaxParticipant] = useState('');
    const [minParticipant, setMinParticipant] = useState('');
    const [createBy, setCreateBy] = useState('');

    const { id } = useParams();

    const activityNameOnChangeHandler = (event) => {
        setActivityName(event.target.value);
    };
    const descriptionOnChangeHandler = (event) => {
        setDescription(event.target.value);
    };

    const locationOnChangeHandler = (event) => {
        setLocation(event.target.value);
    };
    const dateOnChangeHandler = (event) => {
        setDate(event.target.value);
    };
    const timeOnChangeHandler = (event) => {
        setTime(event.target.value);
    };

    const maxParticipantOnChangeHandler = (event) => {
        setMaxParticipant(event.target.value);
    };
    const minParticipantOnChangeHandler = (event) => {
        setMinParticipant(event.target.value);
    };
    const createByOnChangeHandler = (event) => {
        setCreateBy(event.target.value);
    };

    const getActivityData = () => {
        //fetch(`http://localhost:8080/api/activity/${id}`)
        fetch('http://localhost:8080/api/activity/' + 1)
            .then(response => {
                if (response.status === 404) {
                    return Promise.reject(`Received 404 Not Found for Activity `);
                }
                return response.json();
            })
            .then(data => {
                //  setActivityDetails(data);
                setActivityName(data.activityName);
                setDescription(data.description);
                setLocation(data.location);
                setDate(data.date);
                setTime(data.time);
                setMaxParticipant(data.maxParticipant);
                setMinParticipant(data.minParticipant);
                setCreateBy(data.createBy);
            })
            .catch(error => {
                console.log(error);
            });
    }

    useEffect(() => {
        getActivityData();
    }, [id]);

    // console.log(activityName);
    //Include way to only edit/delete activity if the UserId matches the Activities Created User

    const editActivitySubmitHandler = (event) => {
        event.preventDefault();

        const updatedDetail = {
            activityName,
            description,
            location,
            date,
            time,
            maxParticipant,
            minParticipant,
            createBy
        };
        const init = {
            method: 'PUT',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedDetail)
        };
        //fetch(`http://localhost:8080/api/activity/detail/${activityId}`, init)
        fetch('http://localhost:8080/api/activity/' + userStatus.user.userId, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong ');
            })
            .then(data => {
                if (!data) {
                    // redirect the user back to the /home route
                    history('/');
                } else {
                    // we have errors to display
                    // setErrors(data);
                    console.log("This is where the errors would be!")
                }
            })
            .catch(error => console.log(error));
    };

    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>

            <form onSubmit={editActivitySubmitHandler}>
                <div>
                    <h2 className="my-4">Activity</h2>
                    <div>
                        <label htmlFor="activityName">Activity Name</label>
                        <input type="text" id="activityName" name="activityName"
                            value={activityName} onChange={activityNameOnChangeHandler}
                        />
                    </div>

                    <div>
                        <label htmlFor="description">Description</label>
                        <input type="description" id="description" name="description"
                            value={description} onChange={descriptionOnChangeHandler} />
                    </div>

                    <div>
                        <label htmlFor="location">Location of Activity</label>
                        <input type="text" id="location" name="location"
                            value={location} onChange={locationOnChangeHandler} />
                    </div>

                    <div>
                        <label htmlFor="date">Date</label>
                        <input type="date" id="date" name="date"
                            value={date} onChange={dateOnChangeHandler} />
                    </div>

                    <div>
                        <label htmlFor="time">Time</label>
                        <input type="time" id="time" name="time"
                            value={time} onChange={timeOnChangeHandler} />
                    </div>

                    <div>
                        <label htmlFor="maxParticipant">Max # of Participants</label>
                        <input type="number" pattern="[0-9]*" id="maxParticipant"
                            name="maxParticipant" min="6" max="50"
                            value={maxParticipant} onChange={maxParticipantOnChangeHandler} />
                    </div>
                    <div>
                        <label htmlFor="minParticipant">Min # of Participants</label>
                        <input type="number" pattern="[0-9]*" id="minParticipant"
                            name="minParticipant" min="3" max="45"
                            value={minParticipant} onChange={minParticipantOnChangeHandler} />
                    </div>

                    <div>
                        <label htmlFor="createBy">Created By</label>
                        <input type="text" id="createBy" name="createBy"
                            value={createBy} onChange={createByOnChangeHandler} />
                    </div>

                    <div className="mt-5">
                        <button className="btn btn-info" type="submit">
                            <i className="bi bi-save"></i> Update Activity</button>
                        <Link to={`/activity/delete/${activityData.activityId}`} className="btn btn-danger ml-2">
                            <i className="bi bi-x"></i> Delete
                        </Link>
                        <Link to="/activity/browse" className="btn btn-warning ml-2">
                            <i className="bi bi-x"></i> Return To List
                        </Link>
                    </div>
                </div>
            </form>
        </>
    );
};



/*


       setActivityName(data.activityName);
                setDescription(data.description);
                setLocation(data.location);
                setDate(data.data);
                setTime(data.time);
                setUserId(data.userId);
                setMaxParticipant(data.maxParticipant);
                setMinParticipant(data.minParticipant);
                setCreateBy(data.createBy);  

                
    const activityNameOnChangeHandler = (event) => {
        setActivityName(event.target.value);
    };
    const descriptionOnChangeHandler = (event) => {
        setDescription(event.target.value);
    };

    const locationOnChangeHandler = (event) => {
        setLocation(event.target.value);
    };
    const dateOnChangeHandler = (event) => {
        setDate(event.target.value);
    };
    const timeOnChangeHandler = (event) => {
        setTime(event.target.value);
    };

    const maxParticipantOnChangeHandler = (event) => {
        setMaxParticipant(event.target.value);
    };
    const minParticipantOnChangeHandler = (event) => {
        setMinParticipant(event.target.value);
    };

*/