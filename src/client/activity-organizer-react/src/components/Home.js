import { Link, useNavigate, useParams } from 'react-router-dom';


export default function Home() {
  return (
    <main>
      <h1>About Us at So Many Activities</h1>
      <p>Our name may be funny but our Strike Code Force team takes having fun very seriously. 
        That's why we created this app to help people gather together. 
      </p>
      <Link to={`/`} className="btn btn-success btn-sm">
        Return Home
      </Link>

    </main>
  );
}

