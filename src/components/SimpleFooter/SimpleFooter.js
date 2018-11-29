import React from 'react';

const SimpleFooter = (props) => {

    const {githubRepo} = props;

    return (
        <footer className="clay-site-container container-fluid">
            <div className="row">
                <div className="col-6 p-md-0">
                    <ul className="social-icons">
                        <li className="mr-2">
                            <a className="rounded-circle sticker sticker-secondary" href={`${githubRepo}/issues`}  target="_blank" rel="noopener noreferrer">
                                <svg aria-hidden="true" className="lexicon-icon lexicon-icon-bars">
                                    <use xlinkHref="/images/icons/icons.svg#comments" />
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a className="rounded-circle sticker sticker-secondary" href={`${githubRepo}`}  target="_blank" rel="noopener noreferrer">
                                <img className="lexicon-icon" src="/images/home/GitHub-Mark-64px.svg" alt="" />
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </footer>
    )
};

export default SimpleFooter;