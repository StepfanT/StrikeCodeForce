import { useNavigate } from "react-router-dom";

function Errors({ msg }) {
    const history = useNavigate();

    return (
        <p>
            🙅🏾‍♂️ Error{" "}
            {history.location.state ? ` - ${history.location.state.msg}` : ""}
            {msg}
        </p>
    );
}

export default Errors;




// function Errors({ errors }) {
//     if (errors.length === 0) {
//         return null;
//     }

//     return (
//         <div className="alert alert-danger">
//             <ul>
//                 {errors.map(error => (
//                     <li key={error}>{error}</li>
//                 ))}
//             </ul>
//         </div>
//     );
// }

// export default Errors;      