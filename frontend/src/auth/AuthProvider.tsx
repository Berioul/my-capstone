import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import AuthContext from "./AuthContext";

interface Token {
    token: string;
}

interface Param {
    children?: any;
}

export default function AuthProvider({ children }: Param) {

    const [token, setToken] = useState(localStorage.getItem('jwt') ?? '');

    const navigate = useNavigate();

    useEffect(() => {
        localStorage.setItem('jwt', token);
        if (token) {
            setTimeout(() => navigate('/App'));
        } else {
            setTimeout(() => navigate('/'));
        }
    }, [token, navigate]);

    const register = (username: string, password: string, passwordAgain: string) => {
        return fetch(`/api/users`, {
            method: 'POST',
            body: JSON.stringify({
                username,
                password,
                passwordAgain
            }),
            headers: {
                'Content-Type': 'application/json'



            }
        })
    };

    const login = (username: string, password: string) => {
        return fetch(`/api/users/login`, {
            method: 'POST',

            body: JSON.stringify({
                username: username,
                password: password
            }),
            headers: {
                'Content-Type': 'application/json'

            }
        })
            .then(response => {
                if (response.status === 401 || response.status === 403) {
                    throw Error('Benutzername oder Passwort sind falsch');
                }
                return response.json();
            })
            .then((token: Token) => setToken(token.token))


    }

    const logout = () => {
        setToken('');
    };

    return (
        <AuthContext.Provider
            value={{
                token,
                register,
                login,
                logout
            }}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => useContext(AuthContext)