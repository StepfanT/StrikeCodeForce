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
                        <th>First Name</th>
                        <th>Last Name</th>

                    </tr>
                </thead>
                <tbody>
                    {activities.map(activity => (
                        <tr key={activity.userId}>
                            <td>{activity.activityId}</td>
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
                                    <Link to={`/dashboard`} className="btn btn-primary btn-sm">
                                        <i className="bi bi-pencil"></i> Edit
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