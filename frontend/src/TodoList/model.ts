export interface Item{
    subject : string;
    description : string;
    category : string;
    links: Array<Link>
}

interface Link {
    href: string;
    rel: string
}
