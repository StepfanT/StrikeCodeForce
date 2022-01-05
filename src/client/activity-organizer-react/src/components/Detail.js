import { Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState } from "react";
import { getData } from "../testData";
// import Errors from './Errors';

export default function Detail() {
    let activityData = getData();

    const [activityName, setActivityName] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [userId, setUserId] = useState('');
    const [maxParticipant, setMaxParticipant] = useState('');
    const [minParticipant, setMinParticipant] = useState('');
    const [createBy, setCreateBy] = useState('');
    const { activityId } = useParams();
    const history = useNavigate();

    console.log(activityData.activityName);

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

    useEffect(() => {

        fetch(`http://localhost:8080/activity/detail/${activityId}`)
            // Response object
            .then(response => {
                if (response.status === 404) {
                    return Promise.reject(`Received 404 Not Found for Agent ID: ${activityId}`);
                }
                return response.json();
            })
            .then(data => {
                setActivityName(data.activityName);
                setDescription(data.description);
                setLocation(data.location);
                setDate(data.data);
                setTime(data.time);
                setUserId(data.userId);
                setMaxParticipant(data.maxParticipant);
                setMinParticipant(data.minParticipant);
                setCreateBy(data.createBy);
            })
            .catch(error => {
                console.log(error);
            });
    }, [activityId]);


    const editToDoFormSubmitHandler = (event) => {
        event.preventDefault();

        const updatedDetail = {
            activityName,
            description,
            location,
            date,
            time,
            userId,
            maxParticipant,
            minParticipant
        };
        const init = {
            method: 'PUT',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedDetail)
        };
        fetch(`http://localhost:8080/api/agent/${updatedDetail.userId}`, init)
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
                    // redirect the user back to the /todos route
                    history.push('/');
                } else {
                    // we have errors to display
                    // setErrors(data);
                    console.log("This is where the errors would be!")
                }
            })
            .catch(error => console.log(error));
    };

    // <Errors errors={errors} />

    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>
            <div>
                <h2 className="my-4">Activities</h2>
                <table style={{ "borderWidth": "1px", 'borderColor': "#aaaaaa", 'borderStyle': 'solid' }}
                    className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Activity</th>
                            <th>Location</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Owner</th>
                            <th>Description</th>
                            <th>Max Participants</th>
                            <th>Min Participants</th>
                        </tr>
                    </thead>
                    <tbody>
                        {activityData.map(activity => (
                            <tr key={activity.activityId}>
                                <td>{activity.activityName}</td>
                                <td>{activity.location}</td>
                                <td>{activity.date}</td>
                                <td>{activity.time}</td>
                                <td>{activity.createBy}</td>
                                <td>{activity.description}</td>
                                <td>{activity.maxParticipant}</td>
                                <td>{activity.minParticipant}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <Link to={`/activity`} className="btn btn-success btn-sm">
                    Return to List
                </Link>
            </div>
        </>
    );
};



/*


    return (
        <>
            <h2 className="my-4">Edit Activity</h2>
            <form onSubmit={editToDoFormSubmitHandler}>
                <div>
                    <label htmlFor="activityName">Activity Name</label>
                    <input type="text" id="activityName" name="activityName"
                        value={activityName} onChange={activityNameOnChangeHandler}
                        placeholder={activityData.activityName}
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
                <div className="mt-5">
                    <button className="btn btn-info" type="submit">
                        <i className="bi bi-save"></i> Update Activity</button>

                    <Link to="/activity" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i> Cancel
                    </Link>

                </div>
            </form>
        </>
    );
};


     <div>
                    <label htmlFor="createBy">Created By</label>
                    <input type="text" id="createBy" name="createBy"
                        value={createBy} />
                </div>

*/