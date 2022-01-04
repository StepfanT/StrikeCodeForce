import { Link, useNavigate, useParams } from 'react-router-dom';


export default function Home() {
  return (
    <main>
      <h1>About Us</h1>
      <p>Groop Organizer is for organizing groups!</p>
      <Link to={`/`} className="btn btn-success btn-sm">
        Return Home
      </Link>

    </main>
  );
}

