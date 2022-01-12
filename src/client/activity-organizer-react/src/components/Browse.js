import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import AuthContext from "../context/AuthContext"
import { useContext } from "react";
import Posts from './Posts';
import axios from 'axios';
import Pagination from './Pagination';

export default function Browse() {
    const [userStatus, setUserStatus] = useContext(AuthContext);
    const [activities, setActivity] = useState([]);

    const joinActivity = (activityId) => {
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
        fetch('http://localhost:8080/api/activity/user/' + userStatus.user.userId + '/' + activityId, init)
            .then(response => response.json())
            .then(data => { console.log(data); })
            .catch(error => console.log(error));
    }
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage] = useState(5);

    const getActivities = () => {
        setLoading(true);
        fetch('http://localhost:8080/api/activity')
            .then(response => response.json())
            .then(data => { setActivity(data); setPosts(data) })
            .catch(error => console.log(error));
        setLoading(false);
    };

    //UseEffect runs when the component updates or mounts
    useEffect(() => {
        getActivities();

    }, []); //[] contains no dependencies, stops the useEffect
    
    //Get current activities

    const indexOfLastActivity = currentPage * postsPerPage;
    const indexOfFirstActivity = indexOfLastActivity - postsPerPage;
    const currentActivity = posts.slice(indexOfFirstActivity, indexOfLastActivity);

    // Change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber); //pagenumber coming from pagination.js as prop {number}
    
    return (
        <div>
            <h2 className="my-4">Activities</h2>          
            <Posts posts={currentActivity} loading={loading}></Posts>
            <Pagination postsPerPage={postsPerPage} totalPosts={posts.length} paginate={paginate} />
        </div>
    );
}
