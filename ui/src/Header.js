export function Header() {

    const pageNames = ['admin', 'users', 'login', 'logout']

    const pages = pageNames.map(page =>
        <div>
            {page}
        </div>
    )

    return (
        <div>
            {pages}
        </div>
    );
}