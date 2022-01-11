import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState,useContext } from 'react';
import AuthContext from "../context/AuthContext";

export default function Create() {

    const [activityName, setActivityName] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [userId, setUserId] = useState(-1);
    const [maxParticipant, setMaxParticipant] = useState(0);
    const [minParticipant, setMinParticipant] = useState(0);
    const [createBy, setCreateBy] = useState('');
    const [userStatus,setUserStatus]=useContext(AuthContext);

    const { activityId } = useParams();
    const [errors, setErrors] = useState([]);

    const history = useNavigate();

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
    const userIdHandler = (event) => {
        setUserId(event.target.value);
    };

    const maxParticipantOnChangeHandler = (event) => {
        setMaxParticipant(parseInt(event.target.value));
    };
    const minParticipantOnChangeHandler = (event) => {
        setMinParticipant(parseInt(event.target.value));
    };
    const createByOnChangeHandler = (event) => {
        setCreateBy(event.target.value);
    };

    const handleAddSubmit = (event) => {
        event.preventDefault();
        //setUserId(userStatus.user.userId);
        var id=userStatus.user.userId;
        console.log(id);
        const newActivity = {
            activityName,
            description,
            location,
            date,
            time,
            "max":maxParticipant,
            "min":minParticipant,
            createBy,
            "userId":id
        };
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Authorization":"Bearer "+localStorage.getItem("token")
            },
            
            body: JSON.stringify(newActivity)
        };

        fetch('http://localhost:8080/api/activity', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
                // we either created the recorded...
                if (data.activityId) {
                    // redirect the user back to the home page
                    history('/');
                } else {
                    // we have error messages
                    data = Array.from(data);
                    setErrors(data);
                    history('/');
                }
            })
            .catch(error => console.log(error));
    };

    return (
        <>
            <h2 className="my-4">Create Activity</h2>
            <form onSubmit={handleAddSubmit}>

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
                        <i className="bi bi-save"></i> Create Activity</button>

                    <Link to="/activity" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i> Cancel
                    </Link>

                </div>
            </form>
        </>
    )
};