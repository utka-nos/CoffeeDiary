import { useEffect } from 'react';

export function AdminPage() {
  useEffect(() => {
    if (localStorage.getItem("jwt_token") !== "") {
      fetch("http://localhost:8082/api/v1/users/all", {
            method: "GET",
            headers: {
              "Authorization" : "Bearer " + localStorage.getItem("jwt_token")
            }
      })
      .then(response => response.json())
      .then(data => console.log(data))
    }
  }, [])

  return (
    <div>
      Your token is: {localStorage.getItem("jwt_token")}
    </div>
  )

}