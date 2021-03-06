package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "admin_nsmjsf")
public class User implements java.io.Serializable {

	private Integer userId;
	private UserType userType;
	private UserInfo userInfo;
	private String userName;
	private String userPass;
	private String userEmail;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<UserStock> userStocks = new HashSet<UserStock>(0);
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Notification> notifications = new HashSet<Notification>(0);
	private Set<UserPortfolio> userPortfolios = new HashSet<UserPortfolio>(0);
	private Set<PostLike> postLikes = new HashSet<PostLike>(0);
	private Set<UserBullion> userBullions = new HashSet<UserBullion>(0);
	private Set<Post> posts = new HashSet<Post>(0);
	private Set<UserStockSales> userStockSaleses = new HashSet<UserStockSales>(
			0);
	private Set<News> newses = new HashSet<News>(0);
	private Set<CommentSocial> commentSocials = new HashSet<CommentSocial>(0);
	private Set<UserEnergy> userEnergies = new HashSet<UserEnergy>(0);
	private Set<UserWatchList> userWatchLists = new HashSet<UserWatchList>(0);

	public User() {
	}

	public User(UserType userType, UserInfo userInfo, String userName,
			String userPass, String userEmail) {
		this.userType = userType;
		this.userInfo = userInfo;
		this.userName = userName;
		this.userPass = userPass;
		this.userEmail = userEmail;
	}

	public User(UserType userType, UserInfo userInfo, String userName,
			String userPass, String userEmail, Set<Comment> comments,
			Set<UserStock> userStocks, Set<Article> articles,
			Set<Notification> notifications, Set<UserPortfolio> userPortfolios,
			Set<PostLike> postLikes, Set<UserBullion> userBullions,
			Set<Post> posts, Set<UserStockSales> userStockSaleses,
			Set<News> newses, Set<CommentSocial> commentSocials,
			Set<UserEnergy> userEnergies, Set<UserWatchList> userWatchLists) {
		this.userType = userType;
		this.userInfo = userInfo;
		this.userName = userName;
		this.userPass = userPass;
		this.userEmail = userEmail;
		this.comments = comments;
		this.userStocks = userStocks;
		this.articles = articles;
		this.notifications = notifications;
		this.userPortfolios = userPortfolios;
		this.postLikes = postLikes;
		this.userBullions = userBullions;
		this.posts = posts;
		this.userStockSaleses = userStockSaleses;
		this.newses = newses;
		this.commentSocials = commentSocials;
		this.userEnergies = userEnergies;
		this.userWatchLists = userWatchLists;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_user_type_id", nullable = false)
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_info_id", nullable = false)
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_pass", nullable = false)
	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Column(name = "user_email", nullable = false)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserStock> getUserStocks() {
		return this.userStocks;
	}

	public void setUserStocks(Set<UserStock> userStocks) {
		this.userStocks = userStocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserPortfolio> getUserPortfolios() {
		return this.userPortfolios;
	}

	public void setUserPortfolios(Set<UserPortfolio> userPortfolios) {
		this.userPortfolios = userPortfolios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<PostLike> getPostLikes() {
		return this.postLikes;
	}

	public void setPostLikes(Set<PostLike> postLikes) {
		this.postLikes = postLikes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserBullion> getUserBullions() {
		return this.userBullions;
	}

	public void setUserBullions(Set<UserBullion> userBullions) {
		this.userBullions = userBullions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserStockSales> getUserStockSaleses() {
		return this.userStockSaleses;
	}

	public void setUserStockSaleses(Set<UserStockSales> userStockSaleses) {
		this.userStockSaleses = userStockSaleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<News> getNewses() {
		return this.newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<CommentSocial> getCommentSocials() {
		return this.commentSocials;
	}

	public void setCommentSocials(Set<CommentSocial> commentSocials) {
		this.commentSocials = commentSocials;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserEnergy> getUserEnergies() {
		return this.userEnergies;
	}

	public void setUserEnergies(Set<UserEnergy> userEnergies) {
		this.userEnergies = userEnergies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserWatchList> getUserWatchLists() {
		return this.userWatchLists;
	}

	public void setUserWatchLists(Set<UserWatchList> userWatchLists) {
		this.userWatchLists = userWatchLists;
	}

}
