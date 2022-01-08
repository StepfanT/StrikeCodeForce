import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';


export default function Browse() {

    const [activities, setActivity] = useState([]);

    const getActivities = () => {
        fetch('http://localhost:8080/api/activity')
            .then(response => response.json())
            .then(data => setActivity(data))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        getActivities();
    }, []);

    return (
        <div>

            <h2 className="my-4">Activities</h2>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Activity Name</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Description</th>
                        <th>Time</th>
                        <th>Min Participants</th>
                        <th>Max Participants</th>
                        <th>Created By</th>

                    </tr>
                </thead>
                <tbody>
                    {activities.map(activity => (
                        <tr key={activity.userId}>
                            <td>{activity.activityName}</td>
                            <td>{activity.date}</td>
                            <td>{activity.location}</td>
                            <td>{activity.description}</td>
                            <td>{activity.time}</td>
                            <td>{activity.minParticipant}</td>
                            <td>{activity.maxParticipant}</td>
                            <td>{activity.createBy}</td>

                            <td>
                                <div className="float-right">
                                    <Link to={`/activity/detail/${activity.activityId}`} className="btn btn-primary btn-sm">
                                        <i className="bi bi-pencil"></i> Edit
                                    </Link>
                                </div>
                                <div>
                                    <Link to={`/`} className="btn btn-success btn-sm">
                                        <i className="bi bi-pencil"></i>
                                        Return Home
                                    </Link>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

/*

dashboard/${activity.userId}


            <Link to="/agents/add" className="btn btn-primary mb-4">
                <i className="bi bi-plus-circle-fill"></i> Add Agent
            </Link>

*/