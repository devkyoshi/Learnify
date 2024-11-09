import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Home} from "./pages/common/home.jsx";
import {NavigationBar} from "./components/navigation_bar.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <NavigationBar />
            <Routes>
                <Route path="/" element={<Home />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;
