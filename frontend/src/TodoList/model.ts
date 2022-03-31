
export interface Category{
    name: string;
    items: Array<Item>;

}
export interface Item{
    subject : string;
    description : string;
    category : string;
    done:boolean;
    privat:boolean;
    links: Array<Link>
}

interface Link {
    href: string;
    rel: string
}
