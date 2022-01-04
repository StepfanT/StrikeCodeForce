import { Link, useNavigate, useParams } from 'react-router-dom';

export default function Contact() {
    return (
        <>
            <h2> Contact us via telegram!.</h2>
            <Link to={`/`} className="btn btn-success btn-sm">
                Return Home
            </Link>
        </>
    );
}
