import { Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState, useContext } from "react";
import AuthContext from "../context/AuthContext"
// import Errors from './Errors';
import { store } from 'react-notifications-component';

export default function Delete() {

    const [activityData, setActivityDetails] = useState([]);
    const [userStatus, setUserStatus] = useContext(AuthContext);
    const history = useNavigate();
    const { activityId } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/api/activity/${activityId}`)
            .then(response => response.json())
            .then(data => {
                setActivityDetails(data);
                console.log(data);
            })
            .catch(error => console.log(error));
    }, []);


    const deleteActivitySubmitHandler = (event) => {
        event.preventDefault();

        const init = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                "Authorization":"Bearer "+localStorage.getItem("token")
            }
        };
        fetch(`http://localhost:8080/api/activity/${activityId}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
                if (!data) {
                    store.addNotification({
                        title: "Deletion Confirmed",
                        message: "Returning to Dashboard",
                        type: "success",
                        insert: "top",
                        container: "top-right",
                        animationIn: ["animate__animated", "animate__fadeIn"],
                        animationOut: ["animate__animated", "animate__fadeOut"],
                        dismiss: {
                            duration: 3000,
                            onScreen: true
                        }
                    });
                    
                    history('/');
                } else {
                    // we have errors to display                  
                    //    setErrors(data);
                    console.log("this is where errors display");
                }
            })
            .catch(error => console.log(error));
    };

    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>
            <form onSubmit={deleteActivitySubmitHandler}>
                <h2 className="my-4">Activities</h2>
                <div>
                    <label htmlFor="activityName">Activity Name</label>
                    <input type="text" id="activityName" name="activityName"
                        value={activityData.activityName} readOnly
                    />
                </div>

                <div>
                    <label htmlFor="description">Description</label>
                    <input type="description" id="description" name="description"
                        value={activityData.description} readOnly />
                </div>

                <div>
                    <label htmlFor="location">Location of Activity</label>
                    <input type="text" id="location" name="location"
                        value={activityData.location} readOnly />
                </div>

                <div>
                    <label htmlFor="date">Date</label>
                    <input type="date" id="date" name="date"
                        value={activityData.date} readOnly />
                </div>

                <div>
                    <label htmlFor="time">Time</label>
                    <input type="time" id="time" name="time"
                        value={activityData.time} readOnly />
                </div>

                <div>
                    <label htmlFor="maxParticipant">Max # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="maxParticipant"
                        name="maxParticipant" min="6" max="50"
                        value={activityData.max} readOnly />
                </div>
                <div>
                    <label htmlFor="minParticipant">Min # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="minParticipant"
                        name="minParticipant" min="3" max="45"
                        value={activityData.min} readOnly />
                </div>

                <div>
                    <label htmlFor="createBy">Created By</label>
                    <input type="text" id="createBy" name="createBy"
                        value={activityData.createBy} readOnly />
                </div>

                <div className="mt-5">
                    <div className="mt-5">
                        <button className="btn btn-danger" type="submit">
                            <i className="bi bi-save"></i> Delete Activity?</button>

                        <Link to="/" className="btn btn-warning ml-2">
                            <i className="bi bi-x"></i> Cancel
                        </Link>
                    </div>
                </div>
            </form>
        </>
    );
};