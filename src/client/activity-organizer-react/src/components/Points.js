import { Link, useNavigate, useParams } from 'react-router-dom';
import {useEffect,useState,useContext} from 'react';
import AuthContext from "../context/AuthContext"

export default function Points() {

    const [points,setPoints]=useState(0);
    const [userStatus, setUserStatus] = useContext(AuthContext);

    const addPoints=function(data){
        var sum=0;
        for(var i=0;i<data.length;i++)
            sum+=data[i].point;
        setPoints(sum);
    }
    const getPoints = ()=>{
        const init=
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization":"Bearer "+localStorage.getItem("token")
            }
        }

        fetch('http://localhost:8080/api/points/' + userStatus.user.userId,init)
             .then(response => response.json())
             .then(data => { addPoints(data);console.log(data); })
             .catch(error => console.log(error));
    };

    useEffect(() => {
         getPoints();
     }, []);


    return (
        <>
            <h2> You have {points} points.</h2>
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>
        </>
    );
}

